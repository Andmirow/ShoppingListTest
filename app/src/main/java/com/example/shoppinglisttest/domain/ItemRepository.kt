package com.example.shoppinglisttest.domain

import androidx.lifecycle.LiveData

interface ItemRepository {

    fun getList() : LiveData<List<Item>>
    suspend fun remiveItem(item : Item)
    suspend fun setItem(item : Item)
    suspend fun findItemById(id : Int) : Item
    suspend fun addItem(item : Item)
}