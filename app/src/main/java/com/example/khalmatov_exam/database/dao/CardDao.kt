package com.example.khalmatov_exam.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.khalmatov_exam.database.entity.CardEntity
import com.example.khalmatov_exam.model.Card

@Dao
interface CardDao {
    @Insert
    fun createUser(card: CardEntity)

    @Query("SELECT * FROM cards")
    fun getCards():List<CardEntity>
}