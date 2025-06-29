package com.test.android_todo_app.navigation


import kotlinx.serialization.Serializable


sealed interface NavRoutes {
    @Serializable
    data object Home:NavRoutes

    @Serializable
    data class Add(
      val id: Int?,
      val title: String?,
      val description: String?,
    ):NavRoutes
}
