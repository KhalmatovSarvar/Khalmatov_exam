package com.example.khalmatov_exam.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.khalmatov_exam.R
import com.example.khalmatov_exam.adapter.CardsAdapter
import com.example.khalmatov_exam.database.AppDatabase
import com.example.khalmatov_exam.database.entity.CardEntity
import com.example.khalmatov_exam.model.Card
import com.example.khalmatov_exam.networking.ApiClient
import com.example.khalmatov_exam.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val cards: ArrayList<Card> = ArrayList()
    lateinit var adapter: CardsAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var apiService: ApiService
    private lateinit var appDatabase: AppDatabase
    lateinit var add_btn: ImageView
    var REQUEST_CODE = 1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService = ApiClient.createService(ApiService::class.java)
        appDatabase = AppDatabase.getInstance((this))
//        cards.addAll(appDatabase.cardDao().getCards())
        initViews()
        setObservers()
    }


    private fun initViews() {
        add_btn = findViewById(R.id.btn_add)
        recyclerView = findViewById(R.id.rv_cards)
        recyclerView.layoutManager = LinearLayoutManager(this)
        refreshAdapter()

        add_btn.setOnClickListener {
            openDetailsActivity()
        }

    }

    private fun openDetailsActivity() {
            val intent = Intent(this,DetailsActivity::class.java)
            startActivityForResult(intent,REQUEST_CODE)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            adapter.notifyDataSetChanged()
        }
    }


    private fun setObservers() {
        apiService.getCards().enqueue(object : Callback<ArrayList<Card>?> {
            override fun onResponse(
                call: Call<ArrayList<Card>?>,
                response: Response<ArrayList<Card>?>
            ) {
                cards.addAll(response.body()!!)
                Log.d("RESPONSE", response.body()!!.size.toString())
                adapter.addAll(cards)
            }

            override fun onFailure(call: Call<ArrayList<Card>?>, t: Throwable) {
                Log.d("ERRROORR", t.message.toString())
            }
        })

    }

    private fun refreshAdapter() {
        adapter = CardsAdapter(MainActivity(), cards)
        recyclerView.adapter = adapter
    }
}
