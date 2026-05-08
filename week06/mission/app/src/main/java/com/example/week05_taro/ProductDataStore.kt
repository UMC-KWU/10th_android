package com.example.week03_taro

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "product_store")

object ProductDataStore {

    private val gson = Gson()
    private val productListType = object : TypeToken<List<Product>>() {}.type
    private val productsKey = stringPreferencesKey("products_json")

    private val defaultProducts = listOf(
        Product(
            id = "home_jordan_black",
            imageResId = R.drawable.img_product_jordan_black,
            title = "Air Jordan XXXVI",
            price = "US$185",
            description = "Lightweight basketball shoes designed for explosive moves on the court.",
            shownColor = "Black/Copper",
            styleCode = "DA9053-001",
            isShownInHome = true
        ),
        Product(
            id = "home_jordan_white",
            imageResId = R.drawable.img_product_jordan_white,
            title = "Air Jordan 1 Low",
            price = "US$145",
            description = "A classic low-top silhouette with premium leather and everyday comfort.",
            shownColor = "White/Gray",
            styleCode = "553558-136",
            isShownInHome = true
        ),
        Product(
            id = "home_jordan_mid",
            imageResId = R.drawable.img_product_mid,
            title = "Air Jordan 1 Mid",
            price = "US$125",
            description = "Inspired by the original AJ1, this mid-top edition maintains the iconic look you love.",
            shownColor = "White",
            styleCode = "DQ8426-100",
            isShownInHome = true
        ),
        Product(
            id = "shop_socks_1",
            imageResId = R.drawable.img_product_socks,
            title = "Nike Everyday Plus Cushioned",
            subtitle = "Training Ankle Socks (6 Pairs)",
            colorCount = "5 Colours",
            price = "US$10",
            description = "The Nike Everyday Plus Cushioned Socks bring comfort to your workout with extra cushioning under the heel and forefoot and a snug, supportive arch band. Sweat-wicking power and breathability up top help keep your feet dry and cool to help push you through that extra set.",
            shownColor = "Multi-Color",
            styleCode = "SX6897-965",
            isFavorite = true,
            isShownInShop = true
        ),
        Product(
            id = "shop_socks_2",
            imageResId = R.drawable.img_product_socks2,
            title = "Nike Elite Crew",
            subtitle = "Basketball Socks",
            colorCount = "7 Colours",
            price = "US$16",
            description = "Basketball socks designed for support and comfort during play.",
            shownColor = "White/Black",
            styleCode = "DX1234-100",
            isShownInShop = true
        ),
        Product(
            id = "shop_airforce_1",
            imageResId = R.drawable.img_product_airforce,
            title = "Nike Air Force 1 '07",
            subtitle = "Women's Shoes",
            colorCount = "5 Colours",
            price = "US$115",
            badge = "BestSeller",
            description = "The radiance lives on in the Nike Air Force 1 '07, the basketball original that puts a fresh spin on what you know best.",
            shownColor = "White",
            styleCode = "DD8959-100",
            isShownInShop = true
        ),
        Product(
            id = "shop_airforce_2",
            imageResId = R.drawable.img_product_airforce2,
            title = "Nike Air Force 1 '07 Essentials",
            subtitle = "Men's Shoes",
            colorCount = "2 Colours",
            price = "US$115",
            badge = "BestSeller",
            description = "Classic court style with premium materials and everyday comfort.",
            shownColor = "White/Gray",
            styleCode = "HF0001-101",
            isShownInShop = true
        )
    )

    suspend fun seedIfEmpty(context: Context) {
        context.dataStore.edit { preferences ->
            val savedJson = preferences[productsKey]
            if (savedJson.isNullOrBlank()) {
                preferences[productsKey] = gson.toJson(defaultProducts)
            }
        }
    }

    fun getAllProducts(context: Context): Flow<List<Product>> {
        return context.dataStore.data
            .catch { emit(emptyPreferences()) }
            .map { preferences ->
                val json = preferences[productsKey]
                if (json.isNullOrBlank()) {
                    emptyList()
                } else {
                    runCatching {
                        gson.fromJson<List<Product>>(json, productListType) ?: emptyList()
                    }.getOrElse { emptyList() }
                }
            }
    }

    fun getHomeProducts(context: Context): Flow<List<Product>> {
        return getAllProducts(context).map { products ->
            products.filter { it.isShownInHome }
        }
    }

    fun getShopProducts(context: Context): Flow<List<Product>> {
        return getAllProducts(context).map { products ->
            products.filter { it.isShownInShop }
        }
    }

    fun getWishlistProducts(context: Context): Flow<List<Product>> {
        return getAllProducts(context).map { products ->
            products.filter { it.isFavorite }
        }
    }

    suspend fun toggleFavorite(context: Context, productId: String) {
        val currentProducts = getAllProducts(context).first()
        val updatedProducts = currentProducts.map { product ->
            if (product.id == productId) {
                product.copy(isFavorite = !product.isFavorite)
            } else {
                product
            }
        }
        saveProducts(context, updatedProducts)
    }

    private suspend fun saveProducts(context: Context, products: List<Product>) {
        context.dataStore.edit { preferences ->
            preferences[productsKey] = gson.toJson(products)
        }
    }
}