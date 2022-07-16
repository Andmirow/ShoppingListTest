package com.example.shoppinglisttest.domain

class SetItemClass (private val itemRepository: ItemRepository){

    fun setItem(item: Item){
        itemRepository.setItem(item)
    }

}