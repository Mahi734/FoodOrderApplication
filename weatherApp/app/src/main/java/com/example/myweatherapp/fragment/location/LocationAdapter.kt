package com.example.myweatherapp.fragment.location

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.data.RemoteLocation
import com.example.myweatherapp.databinding.ItemContainerLocationBinding

class LocationAdapter(
    private val onLocationClicked: (RemoteLocation) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private val locations = mutableListOf<RemoteLocation>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<RemoteLocation>) {
        locations.clear()
        locations.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationViewHolder {
        return LocationViewHolder(
            ItemContainerLocationBinding.inflate
                (LayoutInflater.from(parent.context),
            parent,
            false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(remoteLocation = locations[position])
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    inner class LocationViewHolder
        (private val binding: ItemContainerLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(remoteLocation: RemoteLocation) {
            with(remoteLocation)
            {
                val location = "$name, $region, $country"
                binding.textRemoteLocation.text
                binding.root.setOnClickListener {
                    onLocationClicked(remoteLocation)
                }
            }
        }


    }
}