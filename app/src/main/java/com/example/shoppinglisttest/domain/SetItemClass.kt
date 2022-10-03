package com.example.shoppinglisttest.domain

class SetItemClass (private val itemRepository: ItemRepository){

    suspend fun setItem(item: Item){
        itemRepository.setItem(item)
    }

}