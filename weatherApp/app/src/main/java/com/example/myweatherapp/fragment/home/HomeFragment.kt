
package com.example.myweatherapp.fragment.home

import android.content.pm.PackageManager
import android.location.Geocoder
import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.clearFragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.myweatherapp.R
import com.example.myweatherapp.data.CurrentLocation
import com.example.myweatherapp.databinding.FragmentHomeBinding
import com.example.myweatherapp.storage.SharedPreferencesManager
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

////#one
class HomeFragment : Fragment() {

    companion object {
        const val REQUEST_KEY_MANUAL_LOCATION_SEARCH = "manuallocationSearch"
        const val KEY_LOCATION_TEXT = "locationText"
        const val KEY_LATITUDE = "latitude"
        const val KEY_LONGITUDE = "logitude"
    }

//    //1st code
    private var _binding: FragmentHomeBinding? = null

    //2nd code
    private val binding get() = requireNotNull(_binding)

//    //use koin here
   private val homeViewModel: HomeViewModel by viewModel()
//
//    //use a location service and get new instance of FLP and return the cureent context of homefragment
    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }
//
//    //get the latitude and logitude for this context
    private val geocoder by lazy {
        Geocoder(requireContext())
    }
//
//    //6th weatherAdapter
    private val weatherDataAdapter = WeatherDataAdapter(
        onLocationClicked = {
            showLocationOptions()
        }
    )
private val sharedPreferencesManager: SharedPreferencesManager by inject()
//
//    ///8th is here not below
    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            getCurrentLocation()
        } else {

            Toast.makeText(requireContext(), "Permission denined", Toast.LENGTH_SHORT).show()
        }
    }
    private var isInitialLocationSet: Boolean = false
//
//
//    //we have binding root here
//    //3rd
    override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
//
//    //set the
//
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set the adapter here
        setWeatherDataAdapter()
        //  setCurrentLocation(currentLocation = sharedPreferencesManager.getCurrentLocation())
        setObservers()
        setListeners()
        if (!isInitialLocationSet) {
            setCurrentLocation(currentLocation = sharedPreferencesManager.getCurrentLocation())
            isInitialLocationSet = true
        }
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            setCurrentLocation(sharedPreferencesManager.getCurrentLocation())
        }
    }

//    //homeViewModle use
//    //main thered if live data is already has data set then its reciver to observer\
//    //if owner moves the state the observe auto matically removes
//    //viewlifecycelOwner gives the fragment view life cycle
//
    private fun setObservers() {
        with(homeViewModel)
        {
            currentLocation.observe(viewLifecycleOwner) {
                val currentLocationDataState = it.getContentIfNotHandled() ?: return@observe
                if (currentLocationDataState.isLoading) {
                    showLoading()
                }
                currentLocationDataState.currentLocation?.let { currentLocation ->
                    hideLoading()
                    sharedPreferencesManager.saveCurrentLocation(currentLocation)
                    setCurrentLocation(currentLocation)
                }
                currentLocationDataState.error?.let { error ->
                    hideLoading()
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                }
            }
            weatherData.observe(viewLifecycleOwner) {
                val weatherDataState = it.getContentIfNotHandled() ?: return@observe
                binding.swipeRefreshLayout.isRefreshing = weatherDataState.isLoading
                weatherDataState.currentWeather?.let { currentWeather ->
                    weatherDataAdapter.setCurrentWeather(currentWeather)
                }
                weatherDataState.forecast?.let { forecasts ->
                    weatherDataAdapter.setForecastData(forecasts)
                }
                weatherDataState.error?.let { error ->
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    //5th
    //setting adapter data to recycler view and make a clickable funcation call
    //binding is our home fragment binding and we get that binding to get funcation and to using binding to recyclerView call and adpater interanally call
// and we store that and call the weatherDataAdapter
    private fun setWeatherDataAdapter() {
        binding.weatherDataRecyclerView.itemAnimator = null
        binding.weatherDataRecyclerView.adapter = weatherDataAdapter
    }

    private fun setCurrentLocation(currentLocation: CurrentLocation? = null) {
        weatherDataAdapter.setCurrentLocation(currentLocation ?: CurrentLocation())
        currentLocation?.let {
            getWeatherData(currentLocation = it)
        }
    }

    //4th
//    currentLocation: CurrentLocation? = null
//    private fun setWeatherData(currentLocation: CurrentLocation? = null) {
//        weatherDataAdapter.setData(
//            data = listOf(
//                currentLocation ?: CurrentLocation()
//            )
//        )
//    }

    //7th get the current location
    private fun getCurrentLocation() {
        //Toast.makeText(requireContext(), "getCurrentLocation()", Toast.LENGTH_SHORT).show()
        homeViewModel.getCurrentLocation(fusedLocationProviderClient, geocoder)
    }

    //9th
    //asscess the context using contextCompat and check the name of permission and its take a 2 params context of current and permission for access
    //and package manager class give the info about the pakages in our current project if permission gives by is grant by the package
    //gives true or false
    private fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    //10TH call the locationPermissionLauncher and execute the launch for activityResultContract and using that permission
    //lauch the popup for permission gives
    private fun requestLocationPermission() {
        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    //11th
    //if true then call the get current location if not the ask for permission
    private fun proceedWithCurrentLocation() {
        if (isLocationPermissionGranted()) {
            getCurrentLocation()

        } else {
            requestLocationPermission()
        }
    }

    //12th
    //its a dialog for this context and top of ui build a dialog for default dialog theme
    //  and use that option on home fragment
    //show is currently show the builder using parameter
    private fun showLocationOptions() {
        val options = arrayOf("Current Location", "Search Manually")
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Chosse our lcoation method")
            setItems(options) { _, which ->
                when (which) {
                    0 -> proceedWithCurrentLocation()
                    1 -> startManualLocationSearch()
                }
            }
            show()
        }


    }

    //13rd
    private fun showLoading() {
        with(binding)
        {
            weatherDataRecyclerView.visibility = View.GONE
            swipeRefreshLayout.isEnabled = false
            swipeRefreshLayout.isRefreshing = true
        }
    }


    //14th
    private fun hideLoading() {
        with(binding)
        {
            weatherDataRecyclerView.visibility = View.GONE
            swipeRefreshLayout.isEnabled = true
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun startManualLocationSearch() {
        startListeningManualLocationSelection()
        findNavController().navigate(R.id.action_home_fragment_to_location_fragment)
    }

    private fun startListeningManualLocationSelection() {
        setFragmentResultListener(REQUEST_KEY_MANUAL_LOCATION_SEARCH)
        { _, bundle ->
            stopListeningManualLocationSelection()
            val currentLocation = CurrentLocation(
                location = bundle.getString(KEY_LOCATION_TEXT) ?: "N/A",
                latitude = bundle.getDouble(KEY_LATITUDE),
                logitude = bundle.getDouble(KEY_LONGITUDE)
            )
            sharedPreferencesManager.saveCurrentLocation(currentLocation)
            setCurrentLocation(currentLocation)
        }
    }

    private fun stopListeningManualLocationSelection() {
        clearFragmentResultListener(REQUEST_KEY_MANUAL_LOCATION_SEARCH)
    }

    private fun getWeatherData(currentLocation: CurrentLocation) {
        if (currentLocation.latitude != null && currentLocation.logitude != null) {
            homeViewModel.getWeatherData(
                latitude = currentLocation.latitude,
                longitude = currentLocation.logitude
            )
        }
    }


}
