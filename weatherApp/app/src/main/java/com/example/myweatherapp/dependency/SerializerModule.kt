package com.example.myweatherapp.dependency

import com.google.gson.Gson
import org.koin.dsl.module

val serializerModule = module {
    single {
        Gson()
    }
}