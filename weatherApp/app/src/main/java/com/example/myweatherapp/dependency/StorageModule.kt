package com.example.myweatherapp.dependency

import com.example.myweatherapp.storage.SharedPreferencesManager
import org.koin.dsl.module

val storageModule = module {
    single { SharedPreferencesManager(context=get(),gson=get()) }
}