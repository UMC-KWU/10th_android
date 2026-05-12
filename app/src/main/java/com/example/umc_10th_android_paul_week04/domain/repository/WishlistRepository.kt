package com.example.umc_10th_android_paul_week04.domain.repository

import com.example.umc_10th_android_paul_week04.data.model.Product
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {
    fun getWishlist(): Flow<List<Product>>
    suspend fun saveWishlist(wishlist: List<Product>)
}