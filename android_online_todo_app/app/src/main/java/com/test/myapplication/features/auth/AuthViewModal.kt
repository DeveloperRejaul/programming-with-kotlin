package com.test.myapplication.features.auth

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
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
                        userDao.insert(UserEntity(name = it.username, email = it.email, accessToken = it.accessToken , id = 1))
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