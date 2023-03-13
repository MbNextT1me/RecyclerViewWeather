package com.example.currentweatherdatabinding.weatherLogic

data class WeatherData (val weather: List<WeatherDescription>, val name: String?, val main: WeatherMain)