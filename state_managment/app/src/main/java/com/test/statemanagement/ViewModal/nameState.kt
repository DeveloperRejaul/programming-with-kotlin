package com.test.statemanagement.ViewModal

import androidx.lifecycle.*

class NameState:ViewModel() {
    private  val _name = MutableLiveData<String>()
    val name:LiveData<String> = _name;

    private  val _last_name = MutableLiveData<String>()
    val last_name:LiveData<String> = _last_name;


    fun updateName (uName:String) {
        _name.value = uName;
    }

    fun updateLastname (lName:String) {
        _last_name.value = lName
    }
}