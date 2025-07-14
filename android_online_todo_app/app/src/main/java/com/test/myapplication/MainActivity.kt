package com.test.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.test.myapplication.core.navigation.Navigation
import com.test.myapplication.core.theme.MyApplicationTheme
import com.test.myapplication.features.home.HomeViewModal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        val homeViewModal = ViewModelProvider(this)[HomeViewModal::class.java]
        setContent {
            MyApplicationTheme {
                Navigation(homeViewModal)
            }
        }
    }
}
