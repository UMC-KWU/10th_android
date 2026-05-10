package com.example.a10th_umc_week02.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10th_umc_week02.R
import com.example.a10th_umc_week02.data.model.BuyData
import com.example.a10th_umc_week02.di.DataStoreModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class BuyViewModel @Inject constructor(
    private val dataStoreModule: DataStoreModule
) : ViewModel() {

    private val _allProducts = MutableStateFlow(
        listOf(
            BuyData("Air Jordan XXXVI", "US$185", R.drawable.ic_blackshoes),
            BuyData("Nike Air Force 1 '07", "US$115", R.drawable.ic_whiteshoes)
        )
    )

    private val _buyList = MutableStateFlow<List<BuyData>>(emptyList())
    val buyList: StateFlow<List<BuyData>> = _buyList

    init {
        observeWishList()
    }

    private fun observeWishList() {
        viewModelScope.launch {
            combine(_allProducts, dataStoreModule.getName()) { all, wish ->
                all.map { product ->
                    product.copy(heart = wish.any { it.name == product.name })
                }
            }.collect { combinedList ->
                _buyList.value = combinedList
            }
        }
    }

    fun toggleWishList(product: BuyData) {
        viewModelScope.launch {
            val currentWishList = dataStoreModule.getName().first().toMutableList()
            val existingItem = currentWishList.find { it.name == product.name }

            if (existingItem != null) {
                currentWishList.removeIf { it.name == product.name }
            } else {
                currentWishList.add(product.copy(heart = true))
            }
            dataStoreModule.saveName(currentWishList)
        }
    }
}