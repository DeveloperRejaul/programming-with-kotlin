package com.test.myapplication.core.navigation

import com.test.myapplication.features.home.HomeModal
import kotlinx.serialization.Serializable


sealed interface Routes {
    @Serializable
    data object Main : Routes

    @Serializable
    data object Login : Routes

    @Serializable
    data object Register : Routes

    @Serializable
    data object Home : Routes

    @Serializable
    data class Task(
        val body: String? = null,
        val id: Int? = null,
        val title: String? = null,
        val userId: Int? = null
    ) : Routes {
        fun isEmpty(): Boolean {
            return body == null && id == null && title == null && userId == null
        }

        fun isNotEmpty(): Boolean = !isEmpty()

        companion object {
            fun from(todo: HomeModal?): Task {
                return Task(
                    body = todo?.body,
                    id = todo?.id,
                    title = todo?.title,
                    userId = todo?.userId
                )
            }
        }
    }
}