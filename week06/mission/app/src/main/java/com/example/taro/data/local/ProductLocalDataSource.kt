package com.example.taro.data.local

import android.content.Context
import com.example.taro.data.model.Product
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun seedIfEmpty() {
        ProductDataStore.seedIfEmpty(context)
    }

    fun getHomeProducts(): Flow<List<Product>> {
        return ProductDataStore.getHomeProducts(context)
    }

    fun getShopProducts(): Flow<List<Product>> {
        return ProductDataStore.getShopProducts(context)
    }

    fun getWishlistProducts(): Flow<List<Product>> {
        return ProductDataStore.getWishlistProducts(context)
    }

    suspend fun toggleFavorite(productId: String) {
        ProductDataStore.toggleFavorite(context, productId)
    }
}