package com.neouul.umc10android.week06.presentation.fragment.home

import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week06.core.base.BaseViewModel
import com.neouul.umc10android.week06.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel<HomeUiState>(HomeUiState()) {

    init {
        loadHomeProducts()
    }

    private fun loadHomeProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(1000) // 가상 지연
            productRepository.getHomeProducts().collect { products ->
                _uiState.update { it.copy(
                    homeProducts = products,
                    isLoading = false
                ) }
            }
        }
    }
}
