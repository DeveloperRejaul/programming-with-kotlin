package com.test.myapplication.features.auth


data class LoginBodyModal(val username:String, val password: String, val expiresInMins: Int)


data class AuthLoginModal (
    val id: Int,
    val accessToken: String,
    val refreshToken: String,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
)

data class RegisterModal(val firstName: String, val lastName: String, val age: Int)

data class RegisterResModal(val id: Int, var firstName: String, var lastName: String, var age: Int)
