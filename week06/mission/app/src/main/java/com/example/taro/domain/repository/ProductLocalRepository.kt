package com.example.taro.domain.repository

import com.example.taro.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductLocalRepository {
    suspend fun seedIfEmpty()

    fun getHomeProducts(): Flow<List<Product>>

    fun getShopProducts(): Flow<List<Product>>

    fun getWishlistProducts(): Flow<List<Product>>

    suspend fun toggleFavorite(productId: String)
}