package com.example.shopinglistapp.domain

class DeleteItemUseCase(private val shopItemRepository: ShopItemRepository) {
    fun deleteItem(item: ShopItem) {
        shopItemRepository.deleteItem(item)
    }
}