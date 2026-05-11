package com.example.taro.ui.wishlist

import com.example.taro.data.model.Product

data class WishlistUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val errorMessage: String? = null
)