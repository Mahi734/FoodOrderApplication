package com.example.myweatherapp.fragment.home

import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.data.CurrentLocation
import com.example.myweatherapp.data.CurrentWeather
import com.example.myweatherapp.data.Forecast
import com.example.myweatherapp.data.LiveDataEvent
import com.example.myweatherapp.repository.WeatherDataRepository
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

//comminucate between diff activity and fragment use viewmodle
//mutable live data is make a data use here
class HomeViewModel(
    private val weatherDataRepository:WeatherDataRepository
) : ViewModel() {

    //2nd
    private val _currentLocation = MutableLiveData<LiveDataEvent<CurrentLocationDataState>>()
    val currentLocation: LiveData<LiveDataEvent<CurrentLocationDataState>> get() = _currentLocation

//5th
fun getCurrentLocation
                (fusedLocationProviderClient:FusedLocationProviderClient,
                 geocoder:Geocoder)
    {
        viewModelScope.launch {
            emitCurrentLocationUiState(isLoading = true)
            weatherDataRepository.getCurrentLocation(
                fusedLocationProviderClient = fusedLocationProviderClient,
                onSuccess = {
                    currentLocation ->
                    updateAddressText(currentLocation,geocoder)
                },
                onFailure = {
                    emitCurrentLocationUiState(error = "Unable to fetch current location")
                }
            )
        }
    }
    //4th code
    //corotine scope
    private  fun updateAddressText(currentLocation: CurrentLocation,geocoder: Geocoder)
    {
        viewModelScope.launch {
            runCatching {
                weatherDataRepository.updateAddressText(currentLocation, geocoder)
            }.onSuccess { location ->
                emitCurrentLocationUiState(currentLocation = location)
            }.onFailure {
                emitCurrentLocationUiState(
                    currentLocation = currentLocation.copy(
                        location = "N/A"
                    )
                )
            }
        }
        }

    //3rd
    //for emitting the current loaction state
    private fun emitCurrentLocationUiState(
        isLoading: Boolean = false,
        currentLocation: CurrentLocation? = null,
        error:String?=null
    ){
        val currentLocationDataState = CurrentLocationDataState(isLoading,currentLocation,error)
        _currentLocation.value=LiveDataEvent(currentLocationDataState)
    }
//end the location thing

    //weather data form home
    private val _weatherData= MutableLiveData<LiveDataEvent<WeatherDataState>>()
    val weatherData: LiveData<LiveDataEvent<WeatherDataState>> get()= _weatherData


    fun getWeatherData(latitude: Double,longitude: Double)
    {
        viewModelScope.launch {
            emitCurrentLocationUiState(isLoading = true)
            weatherDataRepository.getWeatherData(latitude,longitude)?.let {
                weatherData->
                emitWeatherDataUiState(
                    currentWeather = CurrentWeather(
                        icon = weatherData.current.condition.icon,
                        temperature = weatherData.current.temperature,
                        wind = weatherData.current.wind,
                        humidity = weatherData.current.humidity,
                        chanceOfRain = weatherData.forecast.forecastDay.first().day.chanceOfRain
                    ),
                    forecast = weatherData.forecast.
                    forecastDay.first().hour.map {
                        Forecast(
                            time = getForecastTime(it.time),
                            temperature = it.temperature,
                            feelsLikeTemprature = it.feelsLikeTemperature,
                            icon = it.condition.icon
                        )
                    }
                )
            }?:emitWeatherDataUiState(error = "unable to fetch weather data ")
        }
    }

    private fun emitWeatherDataUiState(
        isLoading: Boolean = false,
        currentWeather: CurrentWeather? = null,
        forecast: List<Forecast>? = null,
        error: String? = null
    ){
        val weatherDataState = WeatherDataState(isLoading, currentWeather,forecast,error)
        _weatherData.value=LiveDataEvent(weatherDataState)
    }
//1st
    data class CurrentLocationDataState(
        val isLoading : Boolean,
        val currentLocation: CurrentLocation?,
        val error : String?
    )
    data class WeatherDataState(
        val isLoading: Boolean,
        val currentWeather: CurrentWeather? = null,
        val forecast: List<Forecast>?,
        val error: String?
    )
    private fun getForecastTime(dataTime:String):String{
        val pattern = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val date = pattern.parse(dataTime)?:return dataTime
        return SimpleDateFormat("HH:mm",Locale.getDefault()).format(date)
    }
}