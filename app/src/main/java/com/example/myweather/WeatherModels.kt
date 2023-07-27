package com.example.myweather

data class WeatherResponse (
    val weather: List<Weather>,
    val main:Main,
    val wind:Wind
)

data class Weather(
    val description: String
)

data class Main(
    val temp: Double,
    val feels_like:Double,
    val humidity: Int
)

data class Wind(
    val speed: Double
)