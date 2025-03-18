package com.example.baumanactivity

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val noteText: TextView = itemView.findViewById(R.id.noteText)
}
