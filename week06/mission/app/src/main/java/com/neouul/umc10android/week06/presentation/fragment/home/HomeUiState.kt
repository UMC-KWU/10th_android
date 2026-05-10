package com.neouul.umc10android.week06.presentation.fragment.home

import com.neouul.umc10android.week06.domain.model.Product

data class HomeUiState(
    val homeProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false
)