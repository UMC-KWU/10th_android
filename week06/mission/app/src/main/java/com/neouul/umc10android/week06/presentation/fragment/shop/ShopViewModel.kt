package com.neouul.umc10android.week06.presentation.fragment.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week06.domain.model.Product
import com.neouul.umc10android.week06.domain.repository.ProductRepository
import com.neouul.umc10android.week06.domain.repository.WishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val wishRepository: WishRepository
) : ViewModel() {

    val uiState: StateFlow<ShopUiState> = productRepository.getTotalProducts()
        .map { ShopUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ShopUiState()
        )

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
