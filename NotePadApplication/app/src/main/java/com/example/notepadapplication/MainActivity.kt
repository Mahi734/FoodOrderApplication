package com.example.notepadapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notepadapplication.ViewModel.NotesViewModel
import com.example.notepadapplication.ViewModel.providerFactory.NotesViewModelProviderFactory
import com.example.notepadapplication.database.NotesDatabase
import com.example.notepadapplication.database.NotesRepo

class MainActivity : AppCompatActivity() {

    val viewModel: NotesViewModel by lazy{
        val database = NotesDatabase.getDatabaseInstance(this)
        val notesRepo = NotesRepo(database)
        val notesProviderFactory = NotesViewModelProviderFactory(notesRepo)
        ViewModelProvider(this,notesProviderFactory)[NotesViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}