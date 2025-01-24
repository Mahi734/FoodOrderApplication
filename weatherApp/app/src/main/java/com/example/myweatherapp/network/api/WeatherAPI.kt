package com.example.myweatherapp.network.api

import com.example.myweatherapp.data.RemoteLocation
import com.example.myweatherapp.data.RemoteWeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {


    companion object{
        const val BASE_URL= "https://api.weatherapi.com/v1/"
        const val API_KEY = "e5dae9fc98814e2b94f114942251801"
    }

    @GET("search.json")
    suspend fun searchLocation(
        @Query("key") key:String= API_KEY,
        @Query("q") query:String
    ):Response<List<RemoteLocation>>


    @GET("forecast.json")
    suspend fun getWeatherData
                (
        @Query("key") key:String = API_KEY,
        @Query("q") query: String
    ): Response<RemoteWeatherData>

























}