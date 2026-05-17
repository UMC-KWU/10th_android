package com.example.umc_10th_android_paul_week02

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

        // 이름 설정
private val Context.dataStore by preferencesDataStore(name = "user_wishlist")

class DataStoreManager(private val context: Context) {
    private val gson = Gson()
    private val WISHLIST_KEY = stringPreferencesKey("wishlist_data")

    // 위시리스트 저장 함수
    suspend fun saveWishlist(wishlist: List<Product>) {
        val jsonString = gson.toJson(wishlist)
        context.dataStore.edit { prefs ->
            prefs[WISHLIST_KEY] = jsonString
        }
    }

    // 위시리스트 불러오기 함수
    val wishlistFlow: Flow<List<Product>> = context.dataStore.data.map { prefs ->
        val jsonString = prefs[WISHLIST_KEY] ?: ""
        if (jsonString.isEmpty()) {
            emptyList() // 저장된 게 없으면 빈 리스트 반환
        } else {
            val type = object : TypeToken<List<Product>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }
}