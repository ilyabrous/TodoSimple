package com.example.simpletodo.screens.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpletodo.App
import com.example.simpletodo.model.Note

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var noteLiveData = (application.applicationContext as App).db.noteDao().gelAllLiveData()


}