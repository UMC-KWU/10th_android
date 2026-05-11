package com.example.taro.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taro.domain.repository.ProductLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productLocalRepository: ProductLocalRepository
) : ViewModel() {

    fun seedProductsIfEmpty() {
        viewModelScope.launch {
            runCatching {
                productLocalRepository.seedIfEmpty()
            }.onFailure { throwable ->
                Log.e(TAG, "초기 상품 데이터 저장 실패", throwable)
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}