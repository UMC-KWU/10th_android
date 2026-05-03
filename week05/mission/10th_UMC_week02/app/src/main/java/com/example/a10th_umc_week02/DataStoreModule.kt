package com.example.a10th_umc_week02

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Context의 확장 함수로 선언하여 어디서든 접근 가능하게 만듭니다.
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "ex_name")

class DataStoreModule {
    // 데이터를 식별하기 위한 Key를 만듭니다.
    private val WISH_LIST_KEY = stringPreferencesKey("wish_list")
    private val gson = Gson()

    // 데이터 저장하기
    suspend fun saveName(context: Context, wishList: List<BuyData>) {
        context.dataStore.edit { settings ->
            //객체 -> json
            val jsonString = gson.toJson(wishList) // object는 data class 이름
            // 지정한 key와 일치하는 저장소에 name 값을 저장합니다.
            settings[WISH_LIST_KEY] = jsonString
        }
    }

    // 데이터 가져오기
    fun getName(context: Context): Flow<List<BuyData>> {
        return context.dataStore.data.map { preferences ->
            // 저장된 값이 없으면 "이름 없음"을 반환합니다.
            // 항상 항상 nullable하게 대처를 해야합니다.
            val jsonString = preferences[WISH_LIST_KEY] ?: ""
            //json -> 객체
            val type = object : TypeToken<List<BuyData>>() {}.type
            gson.fromJson(jsonString, type) ?: emptyList()
        }
    }
}