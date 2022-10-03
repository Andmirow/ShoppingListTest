package com.example.shoppinglisttest.domain

class RemiveItemClass (private val itemRepository: ItemRepository){

    suspend fun remiveItem(item : Item){
        itemRepository.remiveItem(item)
    }
}