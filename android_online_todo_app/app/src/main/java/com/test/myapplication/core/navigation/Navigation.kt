package com.test.myapplication.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.myapplication.features.auth.LoginScreen
import com.test.myapplication.features.auth.RegisterScreen
import com.test.myapplication.features.home.CreateTaskScreen
import com.test.myapplication.features.home.HomeScreen
import com.test.myapplication.features.splash.MainScreen


@Composable
fun  Navigation () {
   val  navController = rememberNavController()

    Scaffold{ innerPadding ->
        NavHost(navController = navController, startDestination = Routes.Main, modifier = Modifier.padding(innerPadding)) {
            composable<Routes.Main>(){
                MainScreen()
            }
            composable<Routes.Home>(){
                HomeScreen()
            }
            composable<Routes.Task>(){
                CreateTaskScreen()
            }
            composable<Routes.Login>(){
                LoginScreen()
            }
            composable<Routes.Register>(){
                RegisterScreen()
            }
        }
    }
}