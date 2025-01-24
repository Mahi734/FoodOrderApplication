package com.example.notepadapplication.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepadapplication.data.Note
import com.example.notepadapplication.database.NotesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesRepo: NotesRepo
) : ViewModel() {

    private val _searchNotes = MutableStateFlow<List<Note>>(emptyList())
    val searchNotes: StateFlow<List<Note>> = _searchNotes
    val notes = notesRepo.getNotes()
    fun insertNote(note: Note) = viewModelScope.launch {
        notesRepo.upsertNote(note)
    }
    fun deleteNote(note: Note) = viewModelScope.launch {
        notesRepo.deleteNote(note)
    }
    fun deleteAllNotes() = viewModelScope.launch {
        notesRepo.deleteAllNotes()
    }
    fun searchNotes(searchQuery: String) =
        viewModelScope.launch {
            notesRepo.searchNotes(searchQuery).collect { noteList ->
                _searchNotes.emit(noteList)
            }
        }
}