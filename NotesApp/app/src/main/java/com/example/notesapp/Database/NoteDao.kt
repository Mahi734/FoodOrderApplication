package com.example.notesapp.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

@Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun updateNote(note:Note)

    @Query("SELECT * FROM Note ORDER BY noteId DESC")
    fun selectNotes():Flow<List<Note>>





}