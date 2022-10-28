package com.mf.myshoeapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mf.myshoeapp.models.Shoe

class ShoesListViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList
    var newShoe = Shoe()

    var startValidating: MutableLiveData<Boolean> = MutableLiveData(false)

    fun onPopBack(){
        startValidating.value = false
        newShoe = Shoe()
    }

    fun addNewShoe() {
        if (isValidShoe()) {
            shoesList.value!!.add(newShoe)
        }
    }

    fun isValidShoe(): Boolean {
        return isValidString(newShoe.name) && isValidString(newShoe.company) && isValidString(
            newShoe.description
        ) && isValidDouble(newShoe.size)
    }

    fun isValidString(data: String?): Boolean {
        return !data.isNullOrEmpty()
    }

    fun isValidDouble(data: Double?): Boolean {
        if (data == null) {
            return false
        }
        return data > 0.0
    }


}