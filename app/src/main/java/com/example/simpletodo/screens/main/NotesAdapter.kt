package com.example.simpletodo.screens.main

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.example.simpletodo.App
import com.example.simpletodo.R
import com.example.simpletodo.databinding.ActivityNoteDetailsBinding
import com.example.simpletodo.databinding.ItemNoteListBinding
import com.example.simpletodo.model.Note
import com.example.simpletodo.screens.details.NoteDetailsActivity
import javax.security.auth.callback.CallbackHandler

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

    private lateinit var noteBinding: ItemNoteListBinding

    private var sortedList = SortedList(
            Note::class.java,
            object: SortedListAdapterCallback<Note>(this) {
                override fun compare(o1: Note, o2: Note): Int {
                    if (!o2.done && o1.done) return 1
                    if (o2.done && !o1.done) return -1
                    return (o2.timestamp - o1.timestamp).toInt()
                }

                override fun areContentsTheSame(oldItem: Note?, newItem: Note?): Boolean {
                    return oldItem?.equals(newItem)!!;
                }

                override fun areItemsTheSame(item1: Note?, item2: Note?): Boolean {
                    return item1!!.uid == item2!!.uid;
                }

            }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val _binding = ItemNoteListBinding.inflate(inflater, parent, false)

        _binding.delete.setOnClickListener(this)
        _binding.root.setOnClickListener(this)
        _binding.completed.setOnCheckedChangeListener(this)

//        _binding.delete.setOnClickListener {
//            (parent.context as App).db.noteDao().delete(note)
//        }
//        _binding.root.setOnClickListener {
//            NoteDetailsActivity.start(parent.context as Activity, note)
//        }
//        _binding.completed.setOnCheckedChangeListener { _, completed ->
//            note.done = completed
//            (parent.context as App).db.noteDao().update(note)
//        }

        return NotesViewHolder(_binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = sortedList.get(position)


        with(holder._binding){
            holder.itemView.tag = note
            root.tag = note
            delete.tag = note
            completed.tag = note
            noteText.text = note.text
            completed.isChecked = note.done
        }
    }

    override fun getItemCount() = sortedList.size()

    fun setItems(listOfNotes: List<Note>) {
        sortedList.replaceAll(listOfNotes)
    }

//    private fun updateStrokeOut() {
//        if ( (noteBinding.root.tag as Note).done ){
//            noteBinding.completed.setPaintFlags(noteBinding.noteText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//        } else {
//            noteText.setPaintFlags(noteText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
//        }
//    }

    class NotesViewHolder(val _binding: ItemNoteListBinding)
        : RecyclerView.ViewHolder(_binding.root)

    override fun onClick(v: View) {
        val note = v.tag as Note
        when (v.id) {
            R.id.delete -> {
                (v.context.applicationContext as App).db.noteDao().delete(note)
            }
            R.id.note_item -> {
                NoteDetailsActivity.start(v.context as Activity, note)
            }

        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        val note = buttonView.tag as Note
        when (buttonView.id){
            R.id.completed -> {
                note.done = isChecked
                (buttonView.context.applicationContext as App).db.noteDao().update(note)
            }
        }
    }


}