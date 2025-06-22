package com.test.mvvmapp.model

import kotlinx.serialization.*

@Serializable
data class Post(val userId: Int, val id: Int, val title: String, val body: String)
