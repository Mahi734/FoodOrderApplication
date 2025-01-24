package com.example.myweatherapp.dependency

import android.location.Location
import com.example.myweatherapp.fragment.home.HomeViewModel
import com.example.myweatherapp.fragment.location.LocationViewModel
//import com.example.myweatherapp.fragment.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    //use the view injector
    viewModel {
        HomeViewModel(weatherDataRepository = get())
    }
    viewModel {
        LocationViewModel(weatherDataRepository = get ()) }

    }
