package com.example.umc_10th_android_paul_week04.ui.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umc_10th_android_paul_week04.data.model.Product
import com.example.umc_10th_android_paul_week04.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val wishlistRepository: WishlistRepository
) : ViewModel() {

    // 하트가 눌린(isLiked == true) 아이템만 필터링하여 노출
    val likedProducts: StateFlow<List<Product>> = wishlistRepository.getWishlist()
        .map { list -> list.filter { it.isLiked } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}