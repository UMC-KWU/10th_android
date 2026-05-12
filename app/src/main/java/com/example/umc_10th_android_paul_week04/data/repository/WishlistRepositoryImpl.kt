package com.example.umc_10th_android_paul_week04.data.repository

import com.example.umc_10th_android_paul_week04.data.local.DataStoreManager
import com.example.umc_10th_android_paul_week04.domain.repository.WishlistRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import com.example.umc_10th_android_paul_week04.data.model.Product

class WishlistRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager // Hilt로 주입받음
) : WishlistRepository {
    override fun getWishlist(): Flow<List<Product>> = dataStoreManager.wishlistFlow
    override suspend fun saveWishlist(wishlist: List<Product>) = dataStoreManager.saveWishlist(wishlist)
}