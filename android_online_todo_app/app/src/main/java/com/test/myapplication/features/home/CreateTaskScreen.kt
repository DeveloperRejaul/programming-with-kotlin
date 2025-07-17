package com.test.myapplication.features.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.test.myapplication.core.component.Container


@Composable
fun  CreateTaskScreen(navController: NavHostController, homeViewModal: HomeViewModal) {
    Scaffold { innerPadding ->
        Container (
            modifier = Modifier.padding(innerPadding),
            headerLabel = "Create Task",
            onBack = {navController.popBackStack()}
        ) {
            Text("Create Task Screen")
        }
    }

}