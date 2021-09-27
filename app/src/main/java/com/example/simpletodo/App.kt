package com.example.simpletodo

import android.app.Application
import androidx.room.Room
import com.example.simpletodo.data.AppDatabase

class App : Application() {
   lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        )
            .allowMainThreadQueries()
            .build()
    }
}