package com.neouul.umc10android.week06.presentation.fragment.wish

import com.neouul.umc10android.week06.core.base.UiState
import com.neouul.umc10android.week06.domain.model.Product

data class WishUiState(
    val wishedProducts: List<Product> = emptyList()
) : UiState