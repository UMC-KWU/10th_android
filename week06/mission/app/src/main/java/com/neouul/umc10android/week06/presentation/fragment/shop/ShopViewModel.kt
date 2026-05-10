package com.neouul.umc10android.week06.presentation.fragment.shop

import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week06.core.base.BaseViewModel
import com.neouul.umc10android.week06.domain.model.Product
import com.neouul.umc10android.week06.domain.repository.ProductRepository
import com.neouul.umc10android.week06.domain.repository.WishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val wishRepository: WishRepository
) : BaseViewModel<ShopUiState>(ShopUiState()) {

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            productRepository.getTotalProducts().collect { products ->
                _uiState.update { it.copy(allProducts = products) }
            }
        }
    }

    fun toggleWish(product: Product) {
        val newWishState = !product.isWished
        val updatedProduct = product.copy(isWished = newWishState)

        viewModelScope.launch {
            productRepository.updateTotalProduct(updatedProduct)
            if (newWishState) {
                wishRepository.addWishedProduct(updatedProduct)
            } else {
                wishRepository.removeWishedProduct(updatedProduct)
            }
        }
    }
}
