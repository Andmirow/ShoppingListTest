package com.example.shoppinglisttest.domain

import androidx.lifecycle.LiveData

class GetListItemClass(private val itemRepository: ItemRepository) {


    fun getList(): LiveData<List<Item>> {
        return itemRepository.getList()
    }

}