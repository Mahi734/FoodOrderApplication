package com.example.myweatherapp.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed class WeatherData
    //1st
    data class CurrentLocation(
       val date: String = getCurrentDate(),
        val location: String = "Chosse your location",
        val latitude: Double? = null,
        val logitude: Double? = null
    ) : WeatherData()

    private fun getCurrentDate(): String {
        val currentDate = Date()
        val formatter = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
        return "Today , ${formatter.format(currentDate)}"
    }
    data class CurrentWeather(
        val icon: String,
        val temperature: Float,
        val wind: Float,
        val humidity: Int,
        val chanceOfRain: Int
    ) : WeatherData()

    data class Forecast(
        val time: String,
        val temperature: Float,
        val feelsLikeTemprature: Float,
        val icon: String
    ) : WeatherData()
