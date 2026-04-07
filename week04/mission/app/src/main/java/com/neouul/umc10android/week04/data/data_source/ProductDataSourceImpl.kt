package com.neouul.umc10android.week04.data.data_source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore 정의
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "products_store")

val TOTAL_KEY = stringPreferencesKey("total_key")
val HOME_KEY = stringPreferencesKey("home_key")
val WISH_KEY = stringPreferencesKey("wish_key")


class ProductDataSourceImpl(private val context: Context) : ProductDataSource {
    override fun getTotalProducts(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[TOTAL_KEY] ?: "전체 상품 목록 없음"
        }
    }

    override fun getHomeProducts(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[HOME_KEY] ?: "최신 상품 목록 없음"
        }
    }

    override fun getWishProducts(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[WISH_KEY] ?: "위시리스트 목록 없음"
        }
    }

    override suspend fun updateTotalProduct(products: String) {
        context.dataStore.edit { settings ->
            settings[TOTAL_KEY] = products
        }
    }

    override suspend fun updateHomeProduct(products: String) {
        context.dataStore.edit { settings ->
            settings[HOME_KEY] = products
        }
    }

    override suspend fun updateWishProduct(products: String) {
        context.dataStore.edit { settings ->
            settings[WISH_KEY] = products
        }
    }
}
