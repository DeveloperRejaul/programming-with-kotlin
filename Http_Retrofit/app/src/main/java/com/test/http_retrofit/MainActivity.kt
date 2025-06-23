package com.test.http_retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.test.http_retrofit.ui.theme.Http_RetrofitTheme
import androidx.lifecycle.*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // if we not use viewModal library need to pass data like this
        // var  weatherViewModal = ViewModelProvider(this)[WeatherViewModal::class.java]

        setContent {
            Http_RetrofitTheme {
              // if we not use viewModal library need to pass data like this
              //  WeatherPage(weatherViewModal)
              WeatherPage()
            }
        }
    }
}

