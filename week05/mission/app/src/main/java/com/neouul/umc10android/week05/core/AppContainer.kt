package com.neouul.umc10android.week05.core

import android.content.Context
import com.google.gson.Gson
import com.neouul.umc10android.week05.data.data_source.ProductDataSourceImpl
import com.neouul.umc10android.week05.data.repository.ProductRepositoryImpl
import com.neouul.umc10android.week05.data.repository.WishRepositoryImpl
import com.neouul.umc10android.week05.domain.repository.ProductRepository
import com.neouul.umc10android.week05.domain.repository.WishRepository

class AppContainer(private val context: Context) {
    private val gson = Gson()
    private val productDataSource by lazy { ProductDataSourceImpl(context) }

    val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl(productDataSource, gson)
    }

    val wishRepository: WishRepository by lazy {
        WishRepositoryImpl(productDataSource, gson)
    }
}