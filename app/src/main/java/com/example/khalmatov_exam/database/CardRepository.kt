package com.example.khalmatov_exam.database

import android.content.Context
import com.example.khalmatov_exam.activity.MainActivity
import com.example.khalmatov_exam.database.entity.CardEntity

class CardRepository(context: Context){
    private val dp = AppDatabase.getInstance(context)
    private var cardDao = dp.cardDao()


    fun saveCard(cardEntity: CardEntity){
        cardDao.createCard(cardEntity)
    }

    fun getSavedCards():List<CardEntity>{
        return cardDao.getCards()
    }
}