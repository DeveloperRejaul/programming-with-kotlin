package com.test.myapplication.core.api


import com.test.myapplication.features.splash.SplashModal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthApi {
    @GET("/auth/me")
    suspend fun me (
        @Header("Authorization") token: String
    ): Response<SplashModal>
}