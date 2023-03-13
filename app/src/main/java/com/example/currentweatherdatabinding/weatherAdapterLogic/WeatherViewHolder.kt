package com.example.currentweatherdatabinding.weatherAdapterLogic

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currentweatherdatabinding.R

class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val city: TextView = itemView.findViewById(R.id.city)
    val temp: TextView = itemView.findViewById(R.id.temp)
    val rh: TextView = itemView.findViewById(R.id.RH)
    val icon: ImageView = itemView.findViewById(R.id.wind_and_prec)
}