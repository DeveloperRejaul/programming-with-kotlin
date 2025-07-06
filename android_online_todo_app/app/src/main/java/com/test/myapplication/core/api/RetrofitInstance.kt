package com.test.myapplication.core.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val baseurl = "https://dummyjson.com";

    private fun getInstance (): Retrofit {
        return Retrofit.Builder().baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create()).build()
    }

    var authApi: AuthApi = getInstance().create(AuthApi::class.java)
}