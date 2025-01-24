package com.example.myweatherapp.Utils

import android.app.Application
import com.example.myweatherapp.dependency.networkModule
import com.example.myweatherapp.dependency.repositoryModule
import com.example.myweatherapp.dependency.serializerModule
import com.example.myweatherapp.dependency.storageModule
import com.example.myweatherapp.dependency.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//start a koin application
//appplication start first when no activity and service are intialize
class AppConfig:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@AppConfig)
            modules(listOf(
                repositoryModule, viewModelModule, serializerModule, storageModule, networkModule
            ))
        }
    }

}