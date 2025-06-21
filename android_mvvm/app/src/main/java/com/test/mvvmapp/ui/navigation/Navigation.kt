package com.test.mvvmapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.mvvmapp.ui.screens.DetailsScreen
import com.test.mvvmapp.ui.screens.HomeScreen

@Composable
fun Navigation(navController:NavHostController) {
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home"){
            HomeScreen()
        }
        composable("details"){
            DetailsScreen(navController)
        }
    }
}