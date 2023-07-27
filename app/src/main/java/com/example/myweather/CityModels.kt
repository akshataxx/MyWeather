package com.example.myweather

data class UnsplashResponse(
    val results: List<Result>
)

data class Result(
    val urls: Urls
)

data class Urls(
    val full: String
)

