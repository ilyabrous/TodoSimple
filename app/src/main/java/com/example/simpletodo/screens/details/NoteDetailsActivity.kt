package com.example.simpletodo.screens.details

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.AndroidException
import android.view.Menu
import android.view.MenuItem
import com.example.simpletodo.App
import com.example.simpletodo.R
import com.example.simpletodo.databinding.ActivityNoteDetailsBinding
import com.example.simpletodo.model.Note

class NoteDetailsActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityNoteDetailsBinding
    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNoteDetailsBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        setSupportActionBar(_binding.toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true);


        if (intent.hasExtra(KEY_NOTE)) {
            note = intent.getParcelableExtra(KEY_NOTE)!!
            _binding.text.text =  Editable.Factory.getInstance().newEditable(note.text)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
                android.R.id.home -> finish()
                R.id.action_save -> {

                    if (intent.hasExtra(KEY_NOTE)) {
                            note.text = _binding.text.text.toString()
                            (applicationContext as App).db.noteDao().update(note)
                    }
                    else (applicationContext as App).db.noteDao().insert(note = Note(
                        text = _binding.text.text.toString(),
                        timestamp = System.currentTimeMillis(),
                        done = false
                    ))
                    finish()
                }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val KEY_NOTE = "NoteDetailsActivity.KEY_NOTE"

        fun start(caller: Activity, note: Note?) {
            val intent = Intent(caller, NoteDetailsActivity::class.java)
            if(note != null) intent.putExtra(KEY_NOTE, note)
            caller.startActivity(intent)
        }
    }
}