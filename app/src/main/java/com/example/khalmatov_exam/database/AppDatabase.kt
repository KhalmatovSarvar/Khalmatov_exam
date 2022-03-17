package com.example.khalmatov_exam.database

import android.content.Context
import androidx.room.*
import com.example.khalmatov_exam.database.dao.CardDao
import com.example.khalmatov_exam.database.entity.CardEntity

@Database(entities = [CardEntity::class], version = 1)
@TypeConverters(DataTypeConverter::class)

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