package com.example.loginapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @GET
    fun getUser(): retrofit2.Call<List<MappingData>>

    @POST("/mappingLogin")
    fun postData(
        @Body mappingData: MappingData
//        @Query("email") email:String,
//        @Query("password") password : String,
//        @Query("deviceId") Id : String = "",
//        @Query("locationId") locationId : String = ""
    ):Call<MappingData>
}