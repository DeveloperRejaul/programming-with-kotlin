package com.test.myapplication.features.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.test.myapplication.core.api.NetworkResponse


@Composable
fun MainScreen(navController: NavController, splashModal: SplashViewModal = viewModel(), ){
    val splashResult = splashModal.result

    LaunchedEffect(Unit) {
        splashModal.me(token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3NTE3OTcyNTMsImV4cCI6MTc1MTc5OTA1M30._CSsXyWqAxThOLyzv1Wu3f-fH_sHKH5BH7visujiJKw")

        // lessen navigation event from view modal
        splashModal.navEvent.collect { route ->
            navController.navigate(route) {
                popUpTo(0)
            }
        }
    }

    when(splashResult) {
        is NetworkResponse.Loading ,NetworkResponse.Initial  -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(30.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
        else -> Unit
    }

}

