package com.example.mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm.db.entities.CURRENT_USER_ID
import com.example.mvvm.db.entities.User


@Dao
    interface UserDao {

        // Insert a new user
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(user: User) : Long

        @Query("SELECT * FROM  user WHERE uid=$CURRENT_USER_ID")
        fun getuser(): LiveData<User>
    }
