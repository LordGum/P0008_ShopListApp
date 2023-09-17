package com.example.shopinglistapp.presentation

import androidx.lifecycle.ViewModel
import com.example.shopinglistapp.data.ShopItemRepositoryImpl
import com.example.shopinglistapp.domain.DeleteItemUseCase
import com.example.shopinglistapp.domain.EditShopItemUseCase
import com.example.shopinglistapp.domain.GetAllListUseCase
import com.example.shopinglistapp.domain.ShopItem

class MainViewModel: ViewModel() {
    private val repository = ShopItemRepositoryImpl

    private val getAllItemUseCase = GetAllListUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getAllItemUseCase.getAllList()

    fun deleteItem(item: ShopItem) {
        deleteItemUseCase.deleteItem(item)
    }
    fun editShopItem(item: ShopItem) {
        val newItem = item.copy(enabled = !item.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}