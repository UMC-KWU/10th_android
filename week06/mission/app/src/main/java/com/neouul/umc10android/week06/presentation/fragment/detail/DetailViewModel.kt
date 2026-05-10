package com.neouul.umc10android.week06.presentation.fragment.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week06.domain.repository.ProductRepository
import com.neouul.umc10android.week06.domain.repository.WishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val wishRepository: WishRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    private val _wishEvent = MutableSharedFlow<String>()
    val wishEvent: SharedFlow<String> = _wishEvent.asSharedFlow()

    fun loadProduct(productId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(500)
            val products = productRepository.getTotalProducts().first()
            val product = products.find { it.id == productId }
            _uiState.update { it.copy(
                product = product,
                isLoading = false
            ) }
        }
    }

    fun toggleWish() {
        val currentProduct = _uiState.value.product ?: return
        val newWishState = !currentProduct.isWished
        val updatedProduct = currentProduct.copy(isWished = newWishState)

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(500)
            productRepository.updateTotalProduct(updatedProduct)
            if (newWishState) {
                wishRepository.addWishedProduct(updatedProduct)
                _wishEvent.emit("위시리스트에 추가되었습니다.")
            } else {
                wishRepository.removeWishedProduct(updatedProduct)
                _wishEvent.emit("위시리스트에서 제거되었습니다.")
            }
            _uiState.update { it.copy(
                product = updatedProduct,
                isLoading = false
            ) }
        }
    }
}

