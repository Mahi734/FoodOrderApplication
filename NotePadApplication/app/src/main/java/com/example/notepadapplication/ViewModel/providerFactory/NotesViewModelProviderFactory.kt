package com.example.notepadapplication.ViewModel.providerFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notepadapplication.ViewModel.NotesViewModel
import com.example.notepadapplication.database.NotesRepo

class NotesViewModelProviderFactory(
    private val notesRepo:NotesRepo
) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(notesRepo) as T
    }




}