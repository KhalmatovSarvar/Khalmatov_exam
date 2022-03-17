package com.example.khalmatov_exam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.khalmatov_exam.database.dao.CardDao
import com.example.khalmatov_exam.database.entity.CardEntity
import com.example.khalmatov_exam.model.Card

@Database(entities = [CardEntity::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao():CardDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if(instance == null){
                instance = Room.databaseBuilder(context,AppDatabase::class.java,"card.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}