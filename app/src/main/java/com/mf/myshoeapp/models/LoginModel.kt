package com.mf.myshoeapp.models

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.mf.myshoeapp.BR
import kotlinx.android.parcel.Parcelize


@Parcelize
 class LoginModel():Parcelable,BaseObservable(){

    @get:Bindable
    var email: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var password: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
 }
