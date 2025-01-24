package com.example.moviesapplication.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class MovieResponse {
    @Parcelize
    data class MovieResponse(
        @SerializedName("results")
        val movies : List<com.example.moviesapplication.Model.Movie>

    ) : Parcelable {
        constructor() : this(mutableListOf())
    }

}
