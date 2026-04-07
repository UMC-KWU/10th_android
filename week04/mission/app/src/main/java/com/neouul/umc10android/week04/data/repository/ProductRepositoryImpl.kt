package com.neouul.umc10android.week04.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.neouul.umc10android.week04.data.data_source.ProductDataSource
import com.neouul.umc10android.week04.domain.model.Product
import com.neouul.umc10android.week04.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val productDataSource: ProductDataSource,
    private val gson: Gson,
) : ProductRepository {

    private val productListType = object : TypeToken<List<Product>>() {}.type

    override fun getTotalProducts(): Flow<List<Product>> {
        return productDataSource.getTotalProducts().map { json ->
            try {
                gson.fromJson(json, productListType) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    override fun getHomeProducts(): Flow<List<Product>> {
        return productDataSource.getHomeProducts().map { json ->
            try {
                gson.fromJson(json, productListType) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    override suspend fun updateTotalProduct(product: Product) {
        val currentProducts = getTotalProducts().first().toMutableList()
        val index = currentProducts.indexOfFirst { it.id == product.id }
        if (index != -1) {
            currentProducts[index] = product
            val json = gson.toJson(currentProducts)
            productDataSource.updateTotalProduct(json)
        }
    }

    override suspend fun updateHomeProduct(product: Product) {
        val currentProducts = getHomeProducts().first().toMutableList()
        val index = currentProducts.indexOfFirst { it.id == product.id }
        if (index != -1) {
            currentProducts[index] = product
            val json = gson.toJson(currentProducts)
            productDataSource.updateHomeProduct(json)
        }
    }
}
