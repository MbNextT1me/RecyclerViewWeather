package com.example.currentweatherdatabinding.weatherAdapterLogic

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currentweatherdatabinding.R
import com.example.currentweatherdatabinding.weatherLogic.WeatherData
import com.squareup.picasso.Picasso

class WeatherAdapter(private val inflater: LayoutInflater):
    RecyclerView.Adapter<WeatherViewHolder>() {

    var WeatherList = listOf<WeatherData>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view: View = inflater.inflate(R.layout.rv_item, parent, false)
        return WeatherViewHolder(view)
    }

    @android.annotation.SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = WeatherList[position]
        holder.city.text = item.name + ":"
        holder.temp.text = item.main.temp + "°C"
        holder.rh.text = item.main.humidity + "%"
        Picasso.get().load("https://openweathermap.org/img/w/${item.weather[0].icon}.png").into(holder.icon)
    }

    override fun getItemCount(): Int {
        return WeatherList.size
    }
}