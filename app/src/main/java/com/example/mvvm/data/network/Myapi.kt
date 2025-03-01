package com.example.mvvm.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Myapi {
    @FormUrlEncoded
    @POST("login")
    fun userLogin(
     @Field("email")   email:String,
    @Field("password")  password:String
    ): Call<ResponseBody>


    companion object{
        //object callable like function
        operator fun invoke() : Myapi{
            return Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create())
                .build().create(Myapi::class.java)
        }
    }


}