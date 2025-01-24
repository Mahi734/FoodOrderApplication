package com.example.myweatherapp.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myweatherapp.data.CurrentLocation
import com.example.myweatherapp.data.CurrentWeather
import com.example.myweatherapp.data.Forecast
import com.example.myweatherapp.data.WeatherData
import com.example.myweatherapp.databinding.ItemContainerCurrentLocationBinding
import com.example.myweatherapp.databinding.ItemContainerCurrentWeatherBinding
import com.example.myweatherapp.databinding.ItemContainerForecastBinding

class WeatherDataAdapter(
    private val onLocationClicked: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //WeatherDataAdapter.CurrentLocationViewHolder

    private companion object {
        const val INDEX_CURRENT_LOCATION = 0
        const val INDEX_CURRENT_WEATHER = 1
        const val INDEX_FORECAST = 2
    }
   private val weatherData = mutableListOf<WeatherData>()
//
//    //6th
//        @SuppressLint("NotifyDataSetChanged")
//    fun setData(data:List<WeatherData>)
//    {
//        weatherData.clear()
//        weatherData.addAll(data)
//        notifyDataSetChanged()
//    }
    fun setCurrentLocation(currentLocation: CurrentLocation) {
        if (weatherData.isEmpty()) {
            weatherData.add(INDEX_CURRENT_LOCATION, currentLocation)
            notifyItemInserted(INDEX_CURRENT_LOCATION)
        } else {
            weatherData[INDEX_CURRENT_LOCATION] = currentLocation
            notifyItemChanged(INDEX_CURRENT_LOCATION)
        }
    }

    fun setCurrentWeather(currentWeather: CurrentWeather) {
        if (weatherData.getOrNull(INDEX_CURRENT_WEATHER) != null) {
            weatherData[INDEX_CURRENT_LOCATION] = currentWeather
            notifyItemChanged(INDEX_CURRENT_WEATHER)
        } else {
            weatherData.add(INDEX_CURRENT_WEATHER, currentWeather)
            notifyItemChanged(INDEX_CURRENT_WEATHER)
        }
    }

    fun setForecastData(forecast: List<Forecast>) {
        weatherData.removeAll{it is Forecast}
        notifyItemRangeChanged(INDEX_FORECAST, weatherData.size)
        weatherData.addAll(INDEX_FORECAST, forecast)
        notifyItemRangeChanged(INDEX_FORECAST, weatherData.size)
    }

////3rd thing create this
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
//         CurrentLocationViewHolder
    {
        return when (viewType) {
            INDEX_CURRENT_LOCATION -> CurrentLocationViewHolder(
                ItemContainerCurrentLocationBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            INDEX_FORECAST -> ForcastViewHolder(
                ItemContainerForecastBinding.
                inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
            else -> CurrentWeatherViewHolder(
                ItemContainerCurrentWeatherBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
      }
        return CurrentLocationViewHolder(
            ItemContainerCurrentLocationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
          )
        )
    }

    //5th things
    override fun getItemCount(): Int {
        return weatherData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (weatherData[position]) {
            is CurrentLocation -> INDEX_CURRENT_LOCATION
            is CurrentWeather -> INDEX_CURRENT_WEATHER
            is Forecast -> INDEX_FORECAST

        }
    }

    //4th things
    // CurrentLocationViewHolder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CurrentLocationViewHolder -> holder.bind(weatherData[position] as CurrentLocation)

            is CurrentWeatherViewHolder ->  holder.bind(weatherData[position] as CurrentWeather)

            is ForcastViewHolder -> holder.bind(weatherData[position] as Forecast)
        }
//        holder.bind( weatherData[position] as CurrentLocation)
    }

    //2nd things bind the item_container_current_Location

    inner class CurrentLocationViewHolder(
        private val binding: ItemContainerCurrentLocationBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentLocation: CurrentLocation) {
            with(binding)
            {
                textCurrentDate.text = currentLocation.date
                textCurrentLocation.text = currentLocation.location
                imageCurrentLocation.setOnClickListener {
                    onLocationClicked()
                }
                textCurrentLocation.setOnClickListener {
                    onLocationClicked()
                }
            }
        }
    }


    inner class CurrentWeatherViewHolder(
        private val binding: ItemContainerCurrentWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentWeather: CurrentWeather) {
            with(binding)
            {
                imageIcon.load("https:${currentWeather.icon}")
                {
                    crossfade(true)
                }
                textTemperature.text = String.format("%s\u00B0C", currentWeather.temperature)
                textWind.text = String.format("%s km/h", currentWeather.wind)
                textHumidity.text = String.format("%s%%", currentWeather.humidity)
                textChanceOfRain.text = String.format("%s%%", currentWeather.chanceOfRain)
            }
        }
    }

    inner class ForcastViewHolder(
        private val binding: ItemContainerForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: Forecast) {
            with(binding)
            {
                textTime.text = forecast.time
                textTemperature.text = String.format("%s\u00B0C", forecast.temperature)
                textFeelsLikeTemperature.text =
                    String.format("%s\u00B0C", forecast.feelsLikeTemprature)
                imageIcon.load("https:${forecast.icon}")
                {
                    crossfade(true)
                }
            }
        }
    }

}