package com.test.myapplication.features.home

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("/posts")
    suspend fun getTodos (
        @Query("_page") page : Int,
        @Query("_limit") limit : Int
    ): Response<List<HomeModal>>
}
