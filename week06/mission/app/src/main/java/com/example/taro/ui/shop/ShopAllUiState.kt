package com.example.taro.ui.shop

import com.example.taro.data.model.Product

data class ShopAllUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val errorMessage: String? = null
)