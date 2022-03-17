package com.example.khalmatov_exam.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val cardnumber: String,
    val name: String,
    val date: String,
    val cvv: String,
)