package com.test.mvvmapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.mvvmapp.ui.screens.DetailsScreen
import com.test.mvvmapp.ui.screens.HomeScreen

@Composable
fun Navigation(navController:NavHostController) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.HOME,
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.HOME){
                HomeScreen(navController)
            }
            composable(NavRoutes.HOME_DETAIL){
                DetailsScreen(navController)
            }
        }
    }
}