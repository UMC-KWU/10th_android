package com.example.myapplication

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: WishlistRepository
) : ViewModel() {
    private val _isWishlisted = MutableStateFlow(false)
    val isWishlisted: StateFlow<Boolean> = _isWishlisted.asStateFlow()

    fun checkWishlist(product: Product) {
        _isWishlisted.value = repository.isWishlisted(product)
    }

    fun toggle(product: Product) {
        repository.toggle(product)
        _isWishlisted.value = repository.isWishlisted(product)
    }
}
