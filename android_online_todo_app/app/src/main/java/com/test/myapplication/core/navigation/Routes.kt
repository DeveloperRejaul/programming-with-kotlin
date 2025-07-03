package com.test.myapplication.core.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object Login : Routes

    @Serializable
    data object Register : Routes

    @Serializable
    data object Home : Routes

    @Serializable
    data object Task : Routes
}