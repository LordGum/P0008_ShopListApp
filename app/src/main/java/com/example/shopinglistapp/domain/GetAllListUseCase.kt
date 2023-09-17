package com.example.shopinglistapp.domain

import androidx.lifecycle.LiveData

class GetAllListUseCase(private val shopItemRepository: ShopItemRepository) {
    fun getAllList(): LiveData<List<ShopItem>> {
        return shopItemRepository.getAllList()
    }
}