package com.example.myweatherapp.dependency

import com.example.myweatherapp.repository.WeatherDataRepository
import org.koin.dsl.module
//singel use for define a single funcation
//get use for give a instace of weather api
val repositoryModule = module {
    single { WeatherDataRepository(weatherAPI = get() )
    }
}