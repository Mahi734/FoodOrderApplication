package com.example.loginapp

data class MappingData (
val email:String,
    val password:String,
    val deviceId: String = "",
    val locationId : String = ""
)
