package com.example.shopinglistapp.domain

class AddItemUseCase(private val shopItemRepository: ShopItemRepository) {
    fun addItem(item: ShopItem) {
        shopItemRepository.addItem(item)
    }
}