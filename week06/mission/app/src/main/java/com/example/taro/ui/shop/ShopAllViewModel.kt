package com.example.taro.ui.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taro.domain.repository.ProductLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ShopAllViewModel @Inject constructor(
    private val productLocalRepository: ProductLocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShopAllUiState(isLoading = true))
    val uiState: StateFlow<ShopAllUiState> = _uiState.asStateFlow()

    init {
        loadShopProducts()
    }

    private fun loadShopProducts() {
        viewModelScope.launch {
            productLocalRepository.getShopProducts().collect { products ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        products = products,
                        errorMessage = null
                    )
                }
            }
        }
    }

    fun toggleFavorite(productId: String) {
        viewModelScope.launch {
            productLocalRepository.toggleFavorite(productId)
        }
    }
}