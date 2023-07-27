package com.example.myweather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.myweather.UnsplashResponse

interface UnsplashApi {
    @GET("search/photos")
    fun getImages(
        @Query("query") query: String,
        @Query("client_id") clientId: String
    ): Call<UnsplashResponse>
}
