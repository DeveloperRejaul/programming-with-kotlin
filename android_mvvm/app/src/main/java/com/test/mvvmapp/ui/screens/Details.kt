package com.test.mvvmapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DetailsScreen (navController: NavHostController) {
    Column {
        Text(text = "Details Screens")
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Back")
        }
    }
}