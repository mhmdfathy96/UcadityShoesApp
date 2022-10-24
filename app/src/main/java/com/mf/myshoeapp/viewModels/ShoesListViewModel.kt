package com.mf.myshoeapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mf.myshoeapp.models.Shoe

class ShoesListViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList
    private val _onAddNewShoe = MutableLiveData<Boolean>()
    val onAddNewShoe: LiveData<Boolean>
        get() = _onAddNewShoe
    private val _onSave = MutableLiveData<Boolean>()
    val onSave: LiveData<Boolean>
        get() = _onSave
    private val _onCancel = MutableLiveData<Boolean>()
    val onCancel: LiveData<Boolean>
        get() = _onCancel
    var newShoe = Shoe()

    init {
        newShoe = Shoe()
        init()
    }

    fun init() {
        _onAddNewShoe.value = false
        _onSave.value = false
        _onCancel.value = false
    }


    fun onAddNewShoe() {
        _onAddNewShoe.value = true
        newShoe = Shoe()
    }

    fun onSave() {
        _onSave.value = true
    }

    fun onCancel() {
        _onCancel.value = true
    }


    fun addNewShoe() {
        if (isValidShoe()) {
            shoesList.value!!.add(newShoe)
        }
    }

    private fun isValidShoe(): Boolean {
        return isValidString(newShoe.name) && isValidString(newShoe.company) && isValidString(
            newShoe.description
        ) && isValidDouble(newShoe.size)
    }

    fun isValidString(data: String): Boolean {
        return data.isNotEmpty()
    }

    fun isValidDouble(data: Double): Boolean {
        return data > 0.0
    }


}