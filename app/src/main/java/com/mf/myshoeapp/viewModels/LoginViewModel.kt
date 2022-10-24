package com.mf.myshoeapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _onLoginPressed = MutableLiveData<Boolean>()
    val onLoginPressed: LiveData<Boolean>
        get() = _onLoginPressed

    init {
        _onLoginPressed.value=false
    }

     fun onLogin() {
        _onLoginPressed.value = true
    }


    fun isEmailValid(email: String): Boolean {
        var isEmailValid = false
        if (email.contains(".com") && email.contains(
                "@"
            )
        ) {
            isEmailValid = true
        }
        return isEmailValid
    }

    fun isPasswordValid(password: String): Boolean {
        var isPasswordValid = false
        if (password.length >= 8) {
            isPasswordValid = true
        }
        return isPasswordValid
    }

}