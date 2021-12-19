package com.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepo:LoginRepo) : ViewModel(){

    private val _isLoading by lazy { MutableLiveData<Boolean>() }
    val isLoading: LiveData<Boolean> by lazy { _isLoading }

    private val _error by lazy { MutableLiveData<String>() }
    val error: LiveData<String> by lazy { _error }

    private val _responseDataLD by lazy { MutableLiveData<LoginResponse>() }
    val responseDataLD by lazy { _responseDataLD }


    fun onLoginClick(username:String,pass:String) {
        setLoadingState(true)

        viewModelScope.launch {
            loginRepo.validateLoginDetails(username,pass).let {
                onResponseReceived(it)
            }
        }
    }

    fun onResponseReceived(responseData: LoginResponse?) {
        if (responseData!=null) {
            _responseDataLD.postValue(responseData)
            _error.postValue("")
        } else {
            _error.postValue( "Something went wrong")
        }
        setLoadingState(false)
    }

    fun setLoadingState(state: Boolean) {
        _isLoading.postValue(state)
    }

}