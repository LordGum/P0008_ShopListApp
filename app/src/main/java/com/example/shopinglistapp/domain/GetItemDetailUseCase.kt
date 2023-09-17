package com.example.shopinglistapp.domain

class GetItemDetailUseCase(private val shopItemRepository: ShopItemRepository) {
    fun getItemDetail(position: Int):ShopItem {
        return shopItemRepository.getItemDetail(position)
    }
}