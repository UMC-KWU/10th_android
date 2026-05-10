package com.neouul.umc10android.week06.presentation.fragment.detail

import com.neouul.umc10android.week06.domain.model.Product

data class DetailUiState(
    val product: Product? = null,
    val isLoading: Boolean = false
)