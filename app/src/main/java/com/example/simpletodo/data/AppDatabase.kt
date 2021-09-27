package com.example.simpletodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpletodo.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}