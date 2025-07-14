package com.test.myapplication.core.api

import com.test.myapplication.features.auth.AuthApi
import com.test.myapplication.features.home.HomeApi
import com.test.myapplication.features.splash.SplashApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val baseurl = "https://dummyjson.com";
    private const val baseurl2 = "https://jsonplaceholder.typicode.com";

    private fun getInstance (): Retrofit {
        return Retrofit.Builder().baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create()).build()
    }
    private fun getInstance2 (): Retrofit {
        return Retrofit.Builder().baseUrl(baseurl2).addConverterFactory(GsonConverterFactory.create()).build()
    }


    var splashApi: SplashApi = getInstance().create(SplashApi::class.java)
    var authApi: AuthApi = getInstance().create(AuthApi::class.java)
    var homeApi: HomeApi = getInstance2().create(HomeApi::class.java)
}