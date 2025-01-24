package com.example.notepadapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId:Int=0,
    val noteTitle:String,
    val noteText:String,
    val data:Date
)