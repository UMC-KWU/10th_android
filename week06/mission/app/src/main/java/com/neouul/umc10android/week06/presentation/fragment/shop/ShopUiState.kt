package com.neouul.umc10android.week06.presentation.fragment.shop

import com.neouul.umc10android.week06.domain.model.Product

data class ShopUiState(
    val allProducts: List<Product> = emptyList()
)