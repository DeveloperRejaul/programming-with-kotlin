package com.test.http_retrofit
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.http_retrofit.api.NetworkResponse
import com.test.http_retrofit.api.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModal : ViewModel(){
    private val  weatherApi = RetrofitInstance.weatherApi;

    // this is for live data state management
    private val  _weatherResult = MutableLiveData<NetworkResponse<List<WeatherModal>>>()
    val weatherResult: LiveData<NetworkResponse<List<WeatherModal>>> = _weatherResult;

    // this is for build in state management
    var result by mutableStateOf<NetworkResponse<List<WeatherModal>>>(NetworkResponse.Initial)
        private set


    fun getData (id:String) {
        _weatherResult.value = NetworkResponse.Loading;
        result = NetworkResponse.Loading;
        viewModelScope.launch {

          try {
              val res =  weatherApi.getWeather(page = 1 , limit = id.toInt());

              if(res.isSuccessful){
                  // using let check body exists in res
                  res.body().let {
                      _weatherResult.value = NetworkResponse.Success(it as List<WeatherModal>)
                      result = NetworkResponse.Success(it)
                  }
              }else{
                  _weatherResult.value = NetworkResponse.Error("Failed To load data")
                  result = NetworkResponse.Error("Failed To load data")
              }
          }catch (e: Exception) {
              _weatherResult.value = NetworkResponse.Error("Failed To load data")
              result = NetworkResponse.Error("Failed To load data")
          }
        }

    }
}