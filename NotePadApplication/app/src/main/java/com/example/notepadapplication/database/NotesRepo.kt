package com.example.notepadapplication.database

import com.example.notepadapplication.data.Note

class NotesRepo(
    notesDatabase: NotesDatabase
) {
    val notesDao=notesDatabase.noteDao
    suspend fun upsertNote(note:Note) = notesDao.upsertNote(note)
    suspend fun deleteNote(note: Note) = notesDao.deleteNote(note)
    fun getNotes() = notesDao.selectNotes()
    suspend fun deleteAllNotes() = notesDao.deleteAllNotes()
    fun searchNotes(searchQuery:String) = notesDao.searchInNotesTitle(searchQuery)
}