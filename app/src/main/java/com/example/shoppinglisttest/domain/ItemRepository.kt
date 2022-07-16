package com.example.shoppinglisttest.domain

import androidx.lifecycle.LiveData

interface ItemRepository {

    fun getList() : LiveData<List<Item>>
    fun remiveItem(item : Item)
    fun setItem(item : Item)
    fun findItemById(id : Int) : Item
    fun addItem(item : Item)
}