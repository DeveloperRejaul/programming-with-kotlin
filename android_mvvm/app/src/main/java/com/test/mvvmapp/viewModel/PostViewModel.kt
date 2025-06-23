package com.test.mvvmapp.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mvvmapp.repositorie.fetchPost
import com.test.mvvmapp.model.UiState
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class PostViewModel : ViewModel() {
    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set



    init {
        viewModelScope.launch {
            try {
                val posts = fetchPost()
                uiState = UiState.Success(posts)
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error fetching posts", e)
                uiState = UiState.Error("Failed to load post: ${e.message}")
            }
        }
    }
}