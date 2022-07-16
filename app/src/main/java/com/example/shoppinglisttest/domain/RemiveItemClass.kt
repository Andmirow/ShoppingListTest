package com.example.shoppinglisttest.domain

class RemiveItemClass (private val itemRepository: ItemRepository){

    fun remiveItem(item : Item){
        itemRepository.remiveItem(item)
    }
}