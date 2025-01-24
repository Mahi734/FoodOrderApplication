package com.example.applicationweather

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide

class ItemNewsBinding (
    val root: View, // Root view
    private val newsImage: ImageView,
    private val newsTitle: TextView,
   private val newsDescription: TextView
    ) {
        var article: Data? = null
            set(value) {
                field = value
                newsTitle.text = value?.title
                newsDescription.text = value?.description
                value?.urlToImage?.let { imageUrl ->
                    Glide.with(newsImage.context).load(imageUrl).into(newsImage)
                }
            }
    }
