package com.test.myapplication.features.splash

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface SplashApi {
    @GET("/auth/me")
    suspend fun me (
        @Header("Authorization") token: String
    ): Response<SplashModal>
}
