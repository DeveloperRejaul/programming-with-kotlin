package com.test.myapplication.features.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.test.myapplication.core.component.Container


@Composable
fun  CreateTaskScreen(navController: NavHostController) {
    Container {
        Text("Create Task Screen")
    }
}