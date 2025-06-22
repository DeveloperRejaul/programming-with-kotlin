package com.test.mvvmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.test.mvvmapp.ui.navigation.Navigation
import com.test.mvvmapp.ui.theme.MVVMAPPTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMAPPTheme {
                App()
            }
        }
    }
}

@Composable
fun App () {
    val navController = rememberNavController();
    Navigation(navController)
}