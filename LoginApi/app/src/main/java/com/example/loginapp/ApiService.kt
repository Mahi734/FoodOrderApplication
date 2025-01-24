package com.example.loginapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiService {

    private const val BASE_URL = "https://dal-api.trusttags.in/mappinglogin"
    val retrofit: Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build()
    }
    val apiService: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}