package com.example.shoppinglisttest.domain

class FindItemByIdClass (private val itemRepository: ItemRepository) {

    suspend fun findItemById(id : Int): Item {
        return itemRepository.findItemById(id)
    }

}