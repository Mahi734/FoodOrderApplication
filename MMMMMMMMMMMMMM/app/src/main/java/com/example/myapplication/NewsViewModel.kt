package com.example.applicationweather

import androidx.lifecycle.ViewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    val newsArticles: MutableLiveData<List<Data>> = MutableLiveData()

    fun fetchNewsArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            RetrofitClient.apiService.getAllNews().enqueue(object : Callback<List<Data>> {
                override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                    if (response.isSuccessful) {
                        newsArticles.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                    // Handle failure
                }
            })
        }
    }
}
