package com.example.shopinglistapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopinglistapp.data.ShopItemRepositoryImpl
import com.example.shopinglistapp.domain.AddItemUseCase
import com.example.shopinglistapp.domain.EditShopItemUseCase
import com.example.shopinglistapp.domain.GetItemDetailUseCase
import com.example.shopinglistapp.domain.ShopItem

class DetailViewModel: ViewModel() {

    private val repository = ShopItemRepositoryImpl

    private val addItemUseCase = AddItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getItemDetailUseCase = GetItemDetailUseCase(repository)

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _nameError = MutableLiveData<Boolean>()
    val nameError: LiveData<Boolean>
        get() = _nameError

    private val _countError = MutableLiveData<Boolean>()
    val countError: LiveData<Boolean>
        get() = _countError

    private val _shouldFinish = MutableLiveData<Unit>()
    val shouldFinish: LiveData<Unit>
        get() = _shouldFinish


    fun addItem(name: String?, count: String?) {
        val parseName = parseName(name)
        val parseCount = parseCount(count)
        val isCorrectValues = isValid(parseName, parseCount)

        if(isCorrectValues) {
            val item = ShopItem(parseName, parseCount, true)
            addItemUseCase.addItem(item)
            finishWork()
        }
    }
    fun editItem(name: String?, count: String?) {
        val parseName = parseName(name)
        val parseCount = parseCount(count)
        val isCorrectValues = isValid(parseName, parseCount)

        if(isCorrectValues) {
            _shopItem.value?.let {
                val item = it.copy(name = parseName, count = parseCount)
                editShopItemUseCase.editShopItem(item)
                finishWork()
            }
        }
    }
    fun getItemDetail(position: Int) {
        val item = getItemDetailUseCase.getItemDetail(position)
        _shopItem.value = item
    }




    private fun parseName(name: String?): String {
        return name?.trim() ?: ""
    }
    private fun parseCount(count: String?): Int {
        return count?.trim()?.toInt() ?: 0
    }
    private fun isValid(name: String, count: Int): Boolean {
        var result = true
        if(name.isBlank()) {
            result = false
            _nameError.value = true
        }
        if(count <= 0) {
            result = false
            _countError.value = true
        }

        return result
    }


    fun resetNameError() {
        _nameError.value = false
    }
    fun resetCountError() {
        _countError.value = false
    }
    private fun finishWork() {
        _shouldFinish.value = Unit
    }
}