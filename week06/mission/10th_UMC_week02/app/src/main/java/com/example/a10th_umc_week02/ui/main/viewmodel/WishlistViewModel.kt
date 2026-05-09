package com.example.a10th_umc_week02.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10th_umc_week02.data.model.BuyData
import com.example.a10th_umc_week02.di.DataStoreModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val dataStoreModule: DataStoreModule
) : ViewModel() {

    private val _wishList = MutableStateFlow<List<BuyData>>(emptyList())
    val wishList: StateFlow<List<BuyData>> = _wishList

    fun loadWishList() {
        viewModelScope.launch {
            dataStoreModule.getName().collect { list ->
                _wishList.value = list
            }
        }
    }

    fun removeItem(item: BuyData) {
        viewModelScope.launch {
            val currentList = _wishList.value.toMutableList()
            currentList.remove(item)
            dataStoreModule.saveName(currentList)
        }
    }
}