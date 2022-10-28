package com.mf.myshoeapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mf.myshoeapp.models.LoginModel

class LoginViewModel : ViewModel() {
    var newUser=LoginModel()
    var startValidating: MutableLiveData<Boolean> = MutableLiveData(false)


   fun onDestroy(){
       startValidating.value=false
    }

    fun isValidUser():Boolean{
        return isEmailValid(newUser.email) && isPasswordValid(newUser.password)
    }

    fun isEmailValid(email: String?): Boolean {
        if (email == null ){
            return false
        }
        if (email.lowercase().contains(".com") && email.contains(
                "@"
            )
        ) {
            return true
        }
        return false
    }

    fun isPasswordValid(password: String?): Boolean {
        if (password == null ){
            return false
        }
        if (password.length >= 8) {
            return true
        }
        return false
    }

}