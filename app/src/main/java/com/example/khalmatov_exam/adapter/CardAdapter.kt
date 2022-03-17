package com.example.khalmatov_exam.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khalmatov_exam.R
import com.example.khalmatov_exam.model.Card
import com.google.android.material.imageview.ShapeableImageView


class CardsAdapter(
    var context: Context,
    var cards: ArrayList<Card>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(photoList: ArrayList<Card>) {
        cards.addAll(photoList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return CardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CardsViewHolder) {
            holder.bind(position)
        }
    }

    override fun getItemCount(): Int {
        Log.d("TTT", cards.size.toString())
        return cards.size
    }


    inner class CardsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val is_added:ShapeableImageView = view.findViewById(R.id.is_added)
        val tv_name:TextView = view.findViewById(R.id.tv_name)
        val tv_num:TextView = view.findViewById(R.id.tv_card_number)
        val tv_date:TextView = view.findViewById(R.id.tv_date)
        fun bind(position: Int) {
            val card = cards[position]
            if (!card.isAdded){
                is_added.visibility == View.VISIBLE
            }
            tv_name.text = card.name
            tv_date.text = card.date.toString()
            tv_num.text = card.number.toString()
        }
    }

}


