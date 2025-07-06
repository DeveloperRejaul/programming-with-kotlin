package com.test.myapplication.features.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.myapplication.core.api.NetworkResponse


@Composable
fun MainScreen(splashModal: SplashViewModal = viewModel()){
    val splashResult = splashModal.result

    LaunchedEffect(Unit) {
        splashModal.me(token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3NTE3ODQ4MjksImV4cCI6MTc1MTc4NjYyOX0.8o9TvmyvwemnD-pD1FwTKX9H92pj1zS4_6rM1_GXtcY")
    }

    when(splashResult) {
        is NetworkResponse.Loading -> {
            Text("Loading...")
        }
        is NetworkResponse.Error -> {
            Text(splashResult.message)
        }
        is NetworkResponse.Success<SplashModal> -> {
            Text(splashResult.data.toString())
        }
        NetworkResponse.Initial -> {}
    }

}

