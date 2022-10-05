package com.example.shoppinglisttest.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglisttest.data.ItemRepositoryImpl
import com.example.shoppinglisttest.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AddItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ItemRepositoryImpl(application)
    private val findItemByIdClass = FindItemByIdClass(repository)
    private val addItemClass = AddItemClass(repository)
    private val setItemClass = SetItemClass(repository)
    private val scope = CoroutineScope(Dispatchers.IO)

    private val _Item = MutableLiveData<Item>()
    val shopItem: LiveData<Item>
        get() = _Item


    private val _errorInput = MutableLiveData<Boolean>()
    val errorInput : LiveData<Boolean>
        get() {return _errorInput}


    fun findItem(id : Int){
          scope.launch {
              val item = findItemByIdClass.findItemById(id)
              Log.d("item",item.name)
              _Item.postValue(item)

        }
    }

    fun addItem(name: String, count : Int){
        scope.launch {
            if(count>0 &&  !name.isBlank()){
                val newItem = Item(name,count,true)
                addItemClass.addItem(newItem)
            }
        }
    }

    fun setItem(item: Item){
        scope.launch {
            setItemClass.setItem(item)
        }
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

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}