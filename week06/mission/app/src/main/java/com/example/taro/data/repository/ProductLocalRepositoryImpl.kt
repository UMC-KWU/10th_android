package com.example.taro.data.repository

import com.example.taro.data.local.ProductLocalDataSource
import com.example.taro.data.model.Product
import com.example.taro.domain.repository.ProductLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductLocalRepositoryImpl @Inject constructor(
    private val localDataSource: ProductLocalDataSource
) : ProductLocalRepository {

    override suspend fun seedIfEmpty() {
        localDataSource.seedIfEmpty()
    }

    override fun getHomeProducts(): Flow<List<Product>> {
        return localDataSource.getHomeProducts()
    }

    override fun getShopProducts(): Flow<List<Product>> {
        return localDataSource.getShopProducts()
    }

    override fun getWishlistProducts(): Flow<List<Product>> {
        return localDataSource.getWishlistProducts()
    }

    override suspend fun toggleFavorite(productId: String) {
        localDataSource.toggleFavorite(productId)
    }
}