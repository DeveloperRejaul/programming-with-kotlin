package com.test.myapplication.features.splash

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.core.api.NetworkResponse
import com.test.myapplication.core.api.RetrofitInstance
import kotlinx.coroutines.launch

class SplashViewModal: ViewModel() {
    private val  authApi = RetrofitInstance.authApi;


    var result by mutableStateOf<NetworkResponse<SplashModal>>(NetworkResponse.Initial)
        private set

    fun me (token:String) {
        result = NetworkResponse.Loading

        viewModelScope.launch{
            try {
                val res = authApi.me("Bearer $token")

                if(res.isSuccessful){
                    res.body().let {
                        result = NetworkResponse.Success<SplashModal>(it as SplashModal)
                    }
                }

            }catch (e: Exception) {
                Log.i("ERROR_AUTH", e.toString())
                result = NetworkResponse.Error("Failed To load data")
            }
        }
    }
}