package com.example.shopinglistapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglistapp.databinding.ActivityMainBinding

private lateinit var viewModel: MainViewModel
private lateinit var adapter: ShopListAdapter
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setAdapter()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            adapter.submitList(it)
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                val position = viewHolder.layoutPosition
                val item = adapter.currentList[position]
                viewModel.deleteItem(item)
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.rvShopList)

        binding.buttonAddShopItem.setOnClickListener{
            val intent = DetailActivity.newIntentAddItem(this)
            startActivity(intent)
        }

        setContentView(binding.root)
    }

    private fun setAdapter() {
        adapter = ShopListAdapter()
        binding.rvShopList.adapter = adapter
        binding.rvShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_DISABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        binding.rvShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_ENABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        adapter.onLongItemClickListener = {
            viewModel.editShopItem(it)
        }
        adapter.onClickListener = {
            val intent = DetailActivity.newIntentEditItem(this, it.position)
            startActivity(intent)
        }
    }
}