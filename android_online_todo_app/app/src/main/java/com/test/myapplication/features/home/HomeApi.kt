package com.test.myapplication.features.home

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi {
    @GET("/posts")
    suspend fun getTodos (
        @Query("_page") page : Int,
        @Query("_limit") limit : Int
    ): Response<List<HomeModal>>


    @PUT("/posts/{id}")
    suspend fun update(@Path("id") postId:Int, @Body body: HomeModal) :Response<HomeModal>


    @POST("/posts/")
    suspend fun create( @Body body: CreatePostModal) :Response<HomeModal>

    @DELETE("/posts/{id}")
    suspend fun deleteTodo ( @Path("id") postId:Int): Response<Unit>
}
