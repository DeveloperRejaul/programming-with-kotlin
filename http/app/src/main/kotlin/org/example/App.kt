package org.example
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable
data class Post(val userId: Int, val id: Int, val title: String, val body: String)

suspend fun main () {
    val client = HttpClient(CIO)
    val response: HttpResponse = client.get("https://jsonplaceholder.typicode.com/posts")
    val jsoStringData = response.bodyAsText()

    // Manually decode JSON
    val posts: List<Post> = Json.decodeFromString(jsoStringData)

    // 3. Use the data
    for (post in posts){
        println(post.id)
    }

    // Optionally encode it back to JSON
    // val backToJson = Json.encodeToString(posts)
    // println(backToJson)


    client.close()
}
