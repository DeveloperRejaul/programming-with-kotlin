package com.test.http_retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitInstance{
    private const val baseUrl: String = "https://jsonplaceholder.typicode.com" ;
    private fun getInstance (): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }

    var weatherApi: WeatherApi = getInstance().create(WeatherApi::class.java)
}