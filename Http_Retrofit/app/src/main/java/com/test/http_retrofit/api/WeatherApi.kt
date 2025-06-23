package com.test.http_retrofit.api

import com.test.http_retrofit.WeatherModal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/posts")
    suspend fun getWeather (
        @Query("_page") page : Int,
        @Query("_limit") limit : Int
    ): Response<List<WeatherModal>>
}