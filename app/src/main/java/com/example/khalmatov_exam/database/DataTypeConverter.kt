package com.example.khalmatov_exam.database

import androidx.room.TypeConverter
import com.example.khalmatov_exam.database.entity.CardEntity
import com.google.gson.Gson

class DataTypeConverter {
    @TypeConverter
    fun fromObject(card: CardEntity): String {
        return Gson().toJson(card)
    }

    @TypeConverter
    fun toObject(json: String): CardEntity {
        return Gson().fromJson(json, CardEntity::class.java)
    }
}