package com.example.shoppinglisttest.domain

class FindItemByIdClass (private val itemRepository: ItemRepository) {

    fun findItemById(id : Int): Item {
        return itemRepository.findItemById(id)
    }

}