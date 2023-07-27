package com.example.myweather

import android.os.Bundle
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Main Activity class extends App combat activity
 * onCreate method is overridden to set the content view of the activity using setContentView

 */

private const val TAG ="MainActivity"
private const val UNSPLASH_ACCESS_KEY = "YWvqLpD6_DWXpnjnPGbuhneEIbnxYj47pMJY6V9MMEEI"
private const val UNSPLASH_BASE_URL = "https://api.unsplash.com/"

class MainActivity : AppCompatActivity() {
    private lateinit var city: AutoCompleteTextView
    //private lateinit var suggestion: TextView
    private lateinit var temperatureValue: TextView
    private lateinit var humidityValue: TextView
    private lateinit var descriptionValue: TextView
    private lateinit var windValue: TextView
    private lateinit var feelsValue: TextView
    private lateinit var backgroundImageView: ImageView

    private val unsplashApi by lazy {
        Retrofit.Builder()
            .baseUrl(UNSPLASH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //set the xml layout for this activity
        city = findViewById(R.id.SearchBar)
        //suggestion = findViewById(R.id.dropdownSuggestion)
        temperatureValue = findViewById(R.id.temperatureValue)
        humidityValue = findViewById(R.id.humidityValue)
        descriptionValue = findViewById(R.id.descriptionValue)
        windValue = findViewById(R.id.windValue)
        feelsValue = findViewById(R.id.feelsValue)
        backgroundImageView = findViewById(R.id.backgroundImageView)

        val submitButton = findViewById<Button>(R.id.submit)
        submitButton.setOnClickListener { submitButton() } //set click listener
    }

    /**
     * Function to be triggered when submit button is clicked
     */
    private fun submitButton(){
        val selectedCity = city.text.toString()
        fetchWeather(selectedCity)
        fetchBackgroundImage(selectedCity)
    }

    /**
     * Function to fetch weather conditions for the selected city
     */
    private fun fetchWeather(city: String) {
        val weatherApi = WeatherApi.retrofitService
        weatherApi.getWeather(city, "70c239d17904f676d67a81814478e43b", "metric").enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "Response successful weather")
                    val weatherResponse = response.body()
                    temperatureValue.setText("${weatherResponse?.main?.temp} °C")
                    humidityValue.setText("${weatherResponse?.main?.humidity} %")
                    descriptionValue.setText(weatherResponse?.weather?.get(0)?.description)
                    windValue.setText("${weatherResponse?.wind?.speed} m/s")
                    feelsValue.setText("${weatherResponse?.main?.feels_like} °C")
                } else {
                    Log.i(TAG, "Response unsuccessful weather")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.i(TAG, "Response failed")
            }
        })
    }

    private fun fetchBackgroundImage(city:String){
        unsplashApi.getImages(city,"WvqLpD6_DWXpnjnPGbuhneEIbnxYj47pMJY6V9MMEEI").enqueue(object: Callback<UnsplashResponse>{
            override fun onResponse(
                call: Call<UnsplashResponse>,
                response: Response<UnsplashResponse>
            ){
                if(response.isSuccessful) {
                    Log.i(TAG, "Response successful images")
                    val imageUrl = response.body()?.results?.get(0)?.urls?.full
                    imageUrl?.let {
                        loadImageIntoBackground(it)

                    }
                }else{
                    Log.e(TAG, "Failed to fetch unsplash images")

                }
            }
            override fun onFailure(call: Call<UnsplashResponse>, t: Throwable) {
                Log.e(TAG, "Unsplash API call failed", t)
            }
        })
    }
    private fun loadImageIntoBackground(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .into(backgroundImageView)
    }
}











