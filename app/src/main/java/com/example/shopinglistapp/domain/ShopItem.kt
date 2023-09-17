package com.example.shopinglistapp.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    var position: Int = UNDEFINED_POSITION
) {
    companion object {
        const val UNDEFINED_POSITION = -1
    }
}
