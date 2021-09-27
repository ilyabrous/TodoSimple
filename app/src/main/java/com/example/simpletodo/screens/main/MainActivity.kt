package com.example.simpletodo.screens.main;


import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpletodo.R
import com.example.simpletodo.databinding.ActivityMainBinding
import com.example.simpletodo.screens.details.NoteDetailsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        _binding.toolbar.setTitle(R.string.app_name)

        val layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter()
        _binding.included.list.layoutManager = layoutManager
        _binding.included.list.addItemDecoration(DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL
        ))
        _binding.included.list.adapter = adapter

        _binding.fab.setOnClickListener {
            NoteDetailsActivity.start(this, null)
        }
        val vm = ViewModelProvider.AndroidViewModelFactory.getInstance(
            this.application
        ).create(MainActivityViewModel::class.java)

        vm.noteLiveData.observe(this) {
            adapter.setItems(it)
            Log.d("mylog", it.toString())
        }

    }



}
