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
        splashModal.me()

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

