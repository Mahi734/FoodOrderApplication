package com.example.myweatherapp.repository

import android.location.Geocoder
import android.location.Location
import com.example.myweatherapp.data.WeatherData
import com.example.myweatherapp.data.CurrentLocation
import com.example.myweatherapp.data.RemoteLocation
import com.example.myweatherapp.data.RemoteWeatherData
import com.example.myweatherapp.network.api.WeatherAPI
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnFailureListener
import io.reactivex.rxjava3.internal.operators.single.SingleDoOnSuccess
import retrofit2.http.Query
import java.lang.StringBuilder
import kotlin.coroutines.cancellation.CancellationException

class WeatherDataRepository(private val weatherAPI: WeatherAPI) {

    //its a entry point FLP instance of location service class
    //getCurrentLocation gives the current location of phone
    //priority == high
    //if succes the update the latitude and longitude and if not then run failure method
    @Suppress("MissingPermission")
    fun getCurrentLocation(
        fusedLocationProviderClient:FusedLocationProviderClient,
        onSuccess: (currentLocation:CurrentLocation)->Unit,
        onFailure: ()->Unit
        )
    {
        fusedLocationProviderClient.
        getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token).addOnSuccessListener {
                location ->
                location ?: onFailure()
            onSuccess(
                CurrentLocation(
                    latitude = location.latitude,
                    logitude = location.longitude
                )
            )
        }.addOnFailureListener{onFailure() }
    }
//2nd
    //geocoder is use to convert the location to cordinates
    //getFormLocation give the array of location in the form of cordinates
    //give the current location data in the address
    @Suppress("DEPRECATION")
    fun updateAddressText(
        currentLocation: CurrentLocation,
        geocoder: Geocoder
    ):
        CurrentLocation{
        val latitude=currentLocation.latitude?: return  currentLocation
        val longitude = currentLocation.logitude?: return currentLocation
        return  geocoder.getFromLocation(latitude,longitude,1)?.let{
            addresses ->
            val address = addresses[0]
            val addressText = StringBuilder()
            addressText.append(address.locality).append(" ,")
            addressText.append(address.adminArea).append(" ,")
            addressText.append(address.countryName)
            currentLocation.copy(
                location = addressText.toString()
            )
        }?:currentLocation
    }

    suspend fun searchLocation(query: String):List<RemoteLocation>?{
        val response = weatherAPI.searchLocation(query=query)
        return if(response.isSuccessful)
            response.body()
        else
            null
    }
    suspend fun getWeatherData(latitude:Double,longitude: Double):RemoteWeatherData?{
        val response = weatherAPI.getWeatherData(query = "$latitude,$longitude")
        return if(response.isSuccessful) response.body() else null
    }

}