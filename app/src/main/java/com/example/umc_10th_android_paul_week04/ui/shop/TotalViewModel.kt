package com.example.umc_10th_android_paul_week04.ui.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umc_10th_android_paul_week04.R
import com.example.umc_10th_android_paul_week04.data.model.Product
import com.example.umc_10th_android_paul_week04.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TotalViewModel @Inject constructor(
    private val wishlistRepository: WishlistRepository // WishlistRepository 활용
) : ViewModel() {

    // 화면에 보여줄 전체 상품 상태
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    init {
        loadInitialProducts()
    }

    private fun loadInitialProducts() {
        // 기존 TotalFragment에 있던 arrayListOf 로직을 여기로 옮깁니다.
        _products.value = arrayListOf(
            Product(R.drawable.socks_nike, "Nike Everyday Plus...", "Training...", "US$10", false),
            // ... 나머지 상품들 추가
        )
    }

    // 하트 클릭 시 호출할 함수
    fun toggleLike(product: Product) {
        val currentList = _products.value.toMutableList()
        val index = currentList.indexOfFirst { it.title == product.title }
        if (index != -1) {
            currentList[index] = currentList[index].copy(isLiked = !currentList[index].isLiked)
            _products.value = currentList

            // 변경된 리스트를 Repository를 통해 저장
            viewModelScope.launch {
                wishlistRepository.saveWishlist(currentList)
            }
        }
    }
}