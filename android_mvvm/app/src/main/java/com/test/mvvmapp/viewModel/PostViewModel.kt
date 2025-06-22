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
import com.test.mvvmapp.model.Post


class PostViewModel : ViewModel() {
    private var page = 1
    private val limit = 10
    private var allPosts = mutableListOf<Post>()

    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set

    var isLoadingMore by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            try {
                val posts = fetchPost(page,limit)
                allPosts.addAll(posts)
                uiState = UiState.Success(allPosts)
            } catch (e: Exception) {
                Log.e("PostViewModel", "Error fetching posts", e)
                uiState = UiState.Error("Failed to load post: ${e.message}")
            }
        }
    }


    fun more () {
        if (isLoadingMore || uiState is UiState.Loading || uiState !is UiState.Success) return
        Log.e("IS_MORE", "api call")
        isLoadingMore = true
        page++

        viewModelScope.launch {
            try {
                val newPosts = fetchPost(page, limit)
                allPosts.addAll(newPosts)
                uiState = UiState.Success(allPosts)
            }catch (e: Exception) {
                Log.e("PostViewModel", "Error loading more posts", e)
                // Revert page number on failure
                page--
                uiState = UiState.Error("Failed to load more post: ${e.message}")
            }finally {
                isLoadingMore = false
            }
        }
    }
}