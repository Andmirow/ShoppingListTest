package com.example.shoppinglisttest.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglisttest.data.ItemRepositoryImpl
import com.example.shoppinglisttest.domain.*

class AddItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ItemRepositoryImpl(application)
    private val findItemByIdClass = FindItemByIdClass(repository)
    private val addItemClass = AddItemClass(repository)
    private val setItemClass = SetItemClass(repository)


    private val _errorInput = MutableLiveData<Boolean>()
    val errorInput : LiveData<Boolean>
        get() {return _errorInput}


    fun findItem(id : Int): Item{
        return findItemByIdClass.findItemById(id)
    }

    fun addItem(name: String, count : Int){
        if(count>0 &&  !name.isBlank()){
            val newItem = Item(name,count,true)
            addItemClass.addItem(newItem)
        }
    }

    fun setItem(item: Item){
        setItemClass.setItem(item)
    }


    fun chekParametrs(name: String, count : Int): Boolean{
        var result = true
        if(count == 0){
            result = false
        }
        if(name.isBlank()){
            result = false
        }
        _errorInput.value = result
        return result
    }



}