package com.example.baumanactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter() : ListAdapter<String, NoteViewHolder>(StringDiffCallback()) {

    private var onNoteClickListener: ((Int, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = currentList[position]
        holder.noteText.text = note
        holder.itemView.setOnClickListener {
            onNoteClickListener?.invoke(position, note)
        }
    }

    override fun getItemCount(): Int = currentList.size

    fun setOnNoteClickListener(listener: (Int, String) -> Unit) {
        onNoteClickListener = listener
    }
}
