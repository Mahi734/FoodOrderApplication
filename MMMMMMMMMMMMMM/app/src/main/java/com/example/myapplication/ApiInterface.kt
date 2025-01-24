package com.example.applicationweather

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

val YOUR_API_KEY : String = "34d1578f0ce34d24a3832e5695c214cc"

interface ApiInterface {

    @GET("/top-headlines?country=us&apiKey=YOUR_API_KEY")
    suspend fun getAllNews(): Call<List<Data>>
}