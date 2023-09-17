package com.example.shopinglistapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglistapp.R
import com.example.shopinglistapp.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopListAdapter.ShopListViewHolder>(ShopItemDiffCallback()) {
    var onLongItemClickListener: ((ShopItem) -> Unit)? = null
    var onClickListener: ((ShopItem) -> Unit)? = null

    class ShopListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_name)
        val count: TextView = view.findViewById(R.id.tv_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val bgId = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disable
            else -> {
                VIEW_TYPE_UNKNOWN
                throw RuntimeException("viewType is unknown")
            }
        }

        val view: View = LayoutInflater.from(parent.context).inflate(
            bgId,
            parent,
            false
        )
        return ShopListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.name.text = shopItem.name
        holder.count.text = shopItem.count.toString()

        holder.itemView.setOnLongClickListener {
            onLongItemClickListener?.invoke(shopItem)
            true
        }
        holder.itemView.setOnClickListener {
            onClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if(shopItem.enabled) VIEW_TYPE_ENABLED
        else VIEW_TYPE_DISABLED
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0
        const val VIEW_TYPE_UNKNOWN = -1

        const val MAX_POOL_SIZE = 20
    }
}