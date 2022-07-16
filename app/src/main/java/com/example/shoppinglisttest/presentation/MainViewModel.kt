package com.example.shoppinglisttest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglisttest.data.ItemRepositoryImpl
import com.example.shoppinglisttest.data.ItemRepositoryImpl.getList
import com.example.shoppinglisttest.domain.*

class MainViewModel : ViewModel() {

    private val repository = ItemRepositoryImpl

    private val getListItemClass = GetListItemClass(repository)
    private val findItemByIdClass = FindItemByIdClass(repository)
    private val addItemClass = AddItemClass(repository)
    private val setItemClass = SetItemClass(repository)
    private val remiveItemClass = RemiveItemClass(repository)

    val shopList = getListItemClass.getList()

    fun deleteItem(item: Item){
        remiveItemClass.remiveItem(item)
    }

    fun findItem(id : Int): Item{
        return findItemByIdClass.findItemById(id)
    }
    
    fun changeEnable(item: Item){
        var newItem = item.copy(enable = !item.enable)
        setItem(newItem)
    }


    fun addItem(item: Item){
        addItemClass.addItem(item)
    }

    fun setItem(item: Item){
        setItemClass.setItem(item)
    }



}