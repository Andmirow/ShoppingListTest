package com.example.shoppinglisttest.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.shoppinglisttest.data.ItemRepositoryImpl
import com.example.shoppinglisttest.domain.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ItemRepositoryImpl(application)

    private val getListItemClass = GetListItemClass(repository)
    private val setItemClass = SetItemClass(repository)
    private val remiveItemClass = RemiveItemClass(repository)

    val shopList = getListItemClass.getList()

    fun deleteItem(item: Item){
        remiveItemClass.remiveItem(item)
    }

    fun changeEnable(item: Item){
        var newItem = item.copy(enable = !item.enable)
        setItem(newItem)
    }


    fun setItem(item: Item){
        setItemClass.setItem(item)
    }



}