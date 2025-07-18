package com.test.myapplication.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.test.myapplication.features.auth.LoginScreen
import com.test.myapplication.features.auth.RegisterScreen
import com.test.myapplication.features.home.CreateTaskScreen
import com.test.myapplication.features.home.HomeScreen
import com.test.myapplication.features.home.HomeViewModal
import com.test.myapplication.features.splash.MainScreen


@Composable
fun  Navigation(homeViewModal: HomeViewModal) {
   val  navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Main) {
        composable<Routes.Main>(){
            MainScreen(navController)
        }
        composable<Routes.Home>(){
            HomeScreen(navController, homeViewModal)
        }
        composable<Routes.Task>(){ backStackEntry ->
            val params = backStackEntry.toRoute<Routes.Task>()
            CreateTaskScreen(navController, homeViewModal, params)
        }
        composable<Routes.Login>(){
            LoginScreen(navController)
        }
        composable<Routes.Register>(){
            RegisterScreen(navController)
        }
    }
}