package com.example.baumanactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NoteActivity : Activity() {

    private lateinit var noteEditText: EditText
    private lateinit var saveButton: Button
    private var existingNote: String? = null
    private var notePosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        noteEditText = findViewById(R.id.noteEditText)
        saveButton = findViewById(R.id.saveButton)

        existingNote = intent.getStringExtra("noteText")
        notePosition = intent.getIntExtra("notePosition", -1)

        existingNote?.let {
            noteEditText.setText(it)
        }

        saveButton.setOnClickListener {
            val newNoteText = noteEditText.text.toString()
            val resultIntent = Intent().apply {
                putExtra("noteText", newNoteText)
                putExtra("notePosition", notePosition)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
