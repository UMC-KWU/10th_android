package com.example.myapplication

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val repository: WishlistRepository
) : ViewModel() {
    val wishlist: StateFlow<List<Product>> = repository.wishlist

    fun toggle(product: Product) = repository.toggle(product)

    fun isWishlisted(product: Product): Boolean = repository.isWishlisted(product)
}
