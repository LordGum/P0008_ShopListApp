package com.example.shopinglistapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shopinglistapp.domain.ShopItem

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.position == newItem.position
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}