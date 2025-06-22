package com.test.mvvmapp.repositorie

import com.test.mvvmapp.api.client
import com.test.mvvmapp.model.Post
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.*

suspend fun fetchPost(page: Int = 1, limit: Int = 10): List<Post> {
    val response: HttpResponse = client.get("https://jsonplaceholder.typicode.com/posts?_page=${page.toString()}&limit=${limit.toString()}")
    val jsoStringData = response.bodyAsText()

    // Manually decode JSON
    val posts: List<Post> = Json.decodeFromString(jsoStringData)

    return  posts;
}