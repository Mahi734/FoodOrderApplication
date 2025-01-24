package com.example.mvvm.ui.auth

import androidx.lifecycle.LiveData

interface authListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFaliure(message:String )
}