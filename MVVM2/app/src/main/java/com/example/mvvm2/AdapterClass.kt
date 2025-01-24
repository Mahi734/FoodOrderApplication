package com.example.mvvm2

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterClass(private val list : List<NewsFeed>) : RecyclerView.Adapter<AdapterClass.ViewHolder>(){


    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.descriptionText)
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

       val view =  LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout,parent,false)

        return ViewHolder(view)

    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

    }


}