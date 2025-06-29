package com.test.android_todo_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.test.android_todo_app.TodoViewModal
import com.test.android_todo_app.ui.screens.AddScreen
import com.test.android_todo_app.ui.screens.HomeScreen


@Composable
fun Navigator(todoViewModal: TodoViewModal) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.Home) {
        composable<NavRoutes.Home>{ HomeScreen(navController = navController, todoViewModal) }
        composable<NavRoutes.Add>{backStackEntry ->
            val todo = backStackEntry.toRoute<NavRoutes.Add>()
            AddScreen(navController = navController,todoViewModal, todo)
        }
    }
}

