package com.example.notesapp.Database

import androidx.room.Database


@Database(entities = [Note::class], version = 1)
abstract class database : Roomdatabase() {



}