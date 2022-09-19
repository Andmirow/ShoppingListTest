package com.example.shoppinglisttest.presentation

import androidx.lifecycle.ViewModel
import com.example.shoppinglisttest.data.ItemRepositoryImpl
import com.example.shoppinglisttest.domain.*

class MainViewModel : ViewModel() {

    private val repository = ItemRepositoryImpl

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