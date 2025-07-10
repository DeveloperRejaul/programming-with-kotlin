package com.test.myapplication.features.splash

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.test.myapplication.core.api.NetworkResponse
import com.test.myapplication.core.api.RetrofitInstance
import com.test.myapplication.core.database.user.UserDatabase
import com.test.myapplication.core.navigation.Routes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModal(application: Application): AndroidViewModel(application) {
    private val  splashApi = RetrofitInstance.splashApi;


    var result by mutableStateOf<NetworkResponse<SplashModal>>(NetworkResponse.Initial)
        private set

    // local database
    private val db = UserDatabase.getInstance(application)
    private val userDao = db.userDao()


    // Navigation event channel
    private val _navEvent = Channel<Routes>(Channel.BUFFERED)
    val navEvent = _navEvent.receiveAsFlow()

    fun me () {
        result = NetworkResponse.Loading

        viewModelScope.launch{
            try {
               val user = userDao.getUser()

                val res = splashApi.me("Bearer ${user?.accessToken}")

                if(res.isSuccessful){
                    res.body().let {
                        result = NetworkResponse.Success<SplashModal>(it as SplashModal)
                    }
                    _navEvent.send(Routes.Home)
                }else{
                    _navEvent.send(Routes.Login)
                }

            }catch (e: Exception) {
                Log.i("ERROR_AUTH", e.toString())
                result = NetworkResponse.Error("Failed To load data")
                _navEvent.send(Routes.Login)
            }
        }
    }
}