package com.example.shopinglistapp.domain

import androidx.lifecycle.LiveData

interface ShopItemRepository {
    fun addItem(item: ShopItem)

    fun deleteItem(item: ShopItem)

    fun editShopItem(item: ShopItem)

    fun getAllList(): LiveData<List<ShopItem>>

    fun getItemDetail(position: Int):ShopItem
}