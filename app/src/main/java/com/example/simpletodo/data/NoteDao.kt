package com.example.simpletodo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.simpletodo.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note")
    fun gelAllLiveData() : LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE uid IN (:noteIds)")
    fun loadAllByIds(noteIds: IntArray): List<Note>

    @Query("SELECT * FROM note WHERE uid = :uid LIMIT 1")
    fun findById(uid: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg notes: Note)

    @Update
    fun update(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)
}