package com.example.currentweatherdatabinding

// my imports
import com.example.currentweatherdatabinding.weatherLogic.WeatherData
import com.example.currentweatherdatabinding.databinding.ActivityMainBinding
import com.example.currentweatherdatabinding.weatherAdapterLogic.WeatherAdapter
import com.google.gson.Gson
import android.widget.Toast
import kotlinx.coroutines.*

// existed imports
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {
    private var weatherDataList =  mutableListOf<WeatherData>()
    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        weatherAdapter = WeatherAdapter(LayoutInflater.from(this))

        binding.weatherItem.adapter = weatherAdapter
        binding.searchButton.setOnClickListener { buttonListener() }
    }

    private suspend fun loadWeather(city: String?) {
        val api_key = getString(R.string.api_key)
        val api_url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$api_key&units=metric"

        try {
            val stream = withContext(Dispatchers.IO) { URL(api_url).content } as InputStream
            val data = Scanner(stream).nextLine()

            val weather = Gson().fromJson(data, WeatherData::class.java)

            weatherDataList.add(weather)

            withContext(Dispatchers.Main) { weatherAdapter.WeatherList = weatherDataList}
        }

        catch (e: java.lang.Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "You type wrong city, try again!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    public fun buttonListener() {
        val city = binding.cityForSearch.text.toString()
        GlobalScope.launch (Dispatchers.IO) { loadWeather(city) }
    }
}