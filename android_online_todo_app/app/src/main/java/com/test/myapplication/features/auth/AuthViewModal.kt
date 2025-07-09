package com.test.myapplication.features.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.core.api.NetworkResponse
import com.test.myapplication.core.api.RetrofitInstance
import kotlinx.coroutines.launch

class AuthViewModal: ViewModel() {
    private val authApi = RetrofitInstance.authApi

    private val  _result = MutableLiveData<NetworkResponse<AuthLoginModal>>(NetworkResponse.Initial)
    val result: LiveData<NetworkResponse<AuthLoginModal>> = _result;

    fun login (userName: String,password: String) {
        _result.value = NetworkResponse.Loading
        viewModelScope.launch{
            try {
                val res = authApi.login(LoginBodyModal(password = password, username = userName, expiresInMins = 30))
                if(res.isSuccessful) {
                    res.body().let {
                        _result.value = NetworkResponse.Success<AuthLoginModal>(it as AuthLoginModal)
                    }
                }else {
                    _result.value = NetworkResponse.Error("Something went wrong")
                }
            }catch (e: Exception) {
                Log.i("ERROR_AUTH", e.toString())
                _result.value = NetworkResponse.Error("Failed To load data")
            }
        }
    }
}