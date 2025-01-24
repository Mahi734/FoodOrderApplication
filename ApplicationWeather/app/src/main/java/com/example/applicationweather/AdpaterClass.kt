package com.example.applicationweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class AdpaterClass(private val items :  List<Data>):RecyclerView.Adapter<AdpaterClass.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.basic_layout_recyclerview,parent,false)

        return MyViewHolder(view)

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val news = items[position]
        holder.binding.news
    }
    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyViewHolder(val binding: View):
        RecyclerView.ViewHolder(binding.rootView)
}