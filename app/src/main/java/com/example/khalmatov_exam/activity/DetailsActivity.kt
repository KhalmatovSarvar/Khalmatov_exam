package com.example.khalmatov_exam.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

class DetailsActivity : AppCompatActivity() {

    lateinit var name:EditText
    lateinit var number:EditText
    lateinit var date1:EditText
    lateinit var date2:EditText
    lateinit var cvv:EditText
    lateinit var btn_save:Button
    lateinit var adapter: CardsAdapter

    private lateinit var apiService: ApiService
    private lateinit var appDatabase: AppDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        appDatabase = AppDatabase.getInstance((this))
        apiService = ApiClient.createService(ApiService::class.java)
        initViews()
    }

    private fun initViews() {
        name = findViewById(R.id.et_holder_name)
        number = findViewById(R.id.et_card_number)
        date1 = findViewById(R.id.et_months)
        date2 = findViewById(R.id.et_year)
        cvv = findViewById(R.id.et_cvv)
        btn_save = findViewById(R.id.btn_save)

        btn_save.setOnClickListener {
            saveData()
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun saveData() {
        val name = name.text.toString()
        val number = number.text.toString()
        val date1 = date1.text.toString()
        val date2 = date2.text.toString()
        val cvv = cvv.text.toString()

        if(
            name.isEmpty()&&
            number.isEmpty()&&
            date1.isEmpty()&&
            date2.isEmpty()&&
            cvv.isEmpty()
        ){
            Toast.makeText(this,"Fill in the gaps", Toast.LENGTH_SHORT).show()
        }else{
            val newCard = Card(name,date1+date2,true,cvv,number.toLong(),"")
                createCard(newCard)
            val newEntityCard = CardEntity(0,number.toString(),name,date1+date2,cvv)
            appDatabase.cardDao().createCard(newEntityCard)
        }

    }

    private fun createCard(card:Card) {
        apiService.createCard(card)
    }
}