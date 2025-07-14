package com.test.myapplication.features.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.myapplication.core.api.NetworkResponse
import com.test.myapplication.core.api.RetrofitInstance
import com.test.myapplication.core.database.user.UserDatabase
import com.test.myapplication.core.database.user.UserEntity
import kotlinx.coroutines.launch

class AuthViewModal(application: Application) : AndroidViewModel(application) {
    private val authApi = RetrofitInstance.authApi

    // local database
    private val db = UserDatabase.getInstance(application)
    private val userDao = db.userDao()


    private val  _loginResult = MutableLiveData<NetworkResponse<AuthLoginModal>>(NetworkResponse.Initial)
    val loginResult: LiveData<NetworkResponse<AuthLoginModal>> = _loginResult;

    private val  _registerResult = MutableLiveData<NetworkResponse<RegisterResModal>>(NetworkResponse.Initial)
    val registerResult: LiveData<NetworkResponse<RegisterResModal>> = _registerResult;



    fun login (userName: String,password: String) {
        _loginResult.value = NetworkResponse.Loading
        viewModelScope.launch{
            try {
                val res = authApi.login(LoginBodyModal(password = password, username = userName, expiresInMins = 1000))
                if(res.isSuccessful) {
                    res.body().let {
                        _loginResult.value = NetworkResponse.Success<AuthLoginModal>(it as AuthLoginModal)
                        userDao.insert(UserEntity(name = it.username, email = it.email, accessToken = it.accessToken , id = 1))
                    }
                }else {
                    _loginResult.value = NetworkResponse.Error("Something went wrong")
                }
            }catch (e: Exception) {
                Log.i("ERROR_AUTH", e.toString())
                _loginResult.value = NetworkResponse.Error("Failed To load data")
            }
        }
    }


    fun register (params:RegisterModal) {
        _registerResult.value = NetworkResponse.Loading
        viewModelScope.launch{
            try {
                val res = authApi.register(params)
                if(res.isSuccessful) {
                    res.body().let {
                        _registerResult.value = NetworkResponse.Success<RegisterResModal>(it as RegisterResModal)
                    }
                }else {
                    _registerResult.value = NetworkResponse.Error("Something went wrong")
                }
            }catch (e: Exception) {
                Log.i("ERROR_AUTH", e.toString())
                _registerResult.value = NetworkResponse.Error("Failed To create data")
            }
        }
    }
}