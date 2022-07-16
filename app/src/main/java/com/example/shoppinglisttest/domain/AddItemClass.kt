package com.example.shoppinglisttest.domain

class AddItemClass (private val itemRepository: ItemRepository) {

    fun addItem(item : Item){
        itemRepository.addItem(item)
    }

}