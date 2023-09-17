package com.example.shopinglistapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopinglistapp.domain.ShopItem
import com.example.shopinglistapp.domain.ShopItemRepository
import kotlin.random.Random

object ShopItemRepositoryImpl: ShopItemRepository {

    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.position.compareTo(o2.position) })
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var autoIncrementId = 0

    init {
        for(i in 1..30) {
            val shopItem = ShopItem("name $i", i, Random.nextBoolean())
            addItem(shopItem)
        }
    }

    override fun addItem(item: ShopItem) {
        if(item.position == ShopItem.UNDEFINED_POSITION) {
            item.position = autoIncrementId++
        }
        shopList.add(item)
        updateList()
    }

    override fun deleteItem(item: ShopItem) {
        shopList.remove(item)
        updateList()
    }

    override fun editShopItem(item: ShopItem) {
        val oldItem = getItemDetail(item.position)
        deleteItem(oldItem)
        addItem(item)
    }

    override fun getAllList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun getItemDetail(position: Int): ShopItem {
        return shopList.find {
            it.position == position
        } ?: throw RuntimeException("item with position: $position not found \n")
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}