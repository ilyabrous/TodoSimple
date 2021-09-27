package com.example.simpletodo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Note (
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "timestamp") var timestamp: Long,
    @ColumnInfo(name = "done") var done: Boolean,
) : Parcelable