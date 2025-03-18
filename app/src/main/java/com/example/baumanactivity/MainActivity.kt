package com.example.baumanactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var viewModel: MainViewModel

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val noteText = result.data?.getStringExtra("noteText")
                val position = result.data?.getIntExtra("notePosition", -1) ?: -1

                if (noteText != null) {
                    if (position == -1) { // если позиция -1, значит создаем новую заметку
                        viewModel.updateNotes(listOf(noteText))
                    } else { // если позиция не -1, значит редактируем заметку
                        viewModel.updateNoteContent(position, noteText)
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        initViewModel()
    }

    private fun initUi() {
        recyclerView = findViewById(R.id.recyclerView)
        noteAdapter = NoteAdapter()
        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startForResult.launch(intent)
        }

        noteAdapter.setOnNoteClickListener { position, noteText ->
            val intent = Intent(this, NoteActivity::class.java).apply {
                putExtra("noteText", noteText)
                putExtra("notePosition", position)
            }
            startForResult.launch(intent)
        }
    }

    private fun initViewModel() {
        val factory = ViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.notes.observe(this) { notes ->
            noteAdapter.submitList(notes)
        }
    }
}
