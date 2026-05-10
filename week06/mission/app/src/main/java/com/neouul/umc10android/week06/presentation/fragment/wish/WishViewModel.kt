package com.neouul.umc10android.week06.presentation.fragment.wish

import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week06.core.base.BaseViewModel
import com.neouul.umc10android.week06.domain.repository.WishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishViewModel @Inject constructor(
    private val wishRepository: WishRepository,
) : BaseViewModel<WishUiState>(WishUiState()) {

    init {
        fetchWishedProducts()
    }

    private fun fetchWishedProducts() {
        viewModelScope.launch {
            wishRepository.getWishedProductsFlow().collect { products ->
                _uiState.update { it.copy(wishedProducts = products) }
            }
        }
    }
}

