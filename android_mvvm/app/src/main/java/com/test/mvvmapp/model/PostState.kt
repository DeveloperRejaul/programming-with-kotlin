package com.test.mvvmapp.model

sealed class UiState{
    object Loading : UiState()
    data class Success(val post:List<Post>) : UiState()
    data class Error(val message: String) : UiState()
}