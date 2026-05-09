package com.example.a10th_umc_week02.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.a10th_umc_week02.R
import com.example.a10th_umc_week02.data.model.BuyData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class BasketViewModel @Inject constructor() : ViewModel() {

    private val _basketList = MutableStateFlow<List<BuyData>>(emptyList())
    val basketList: StateFlow<List<BuyData>> = _basketList

    init {
        loadBasketItems()
    }

    private fun loadBasketItems() {
        _basketList.value = listOf(
            BuyData("Air Jordan XXXVI", "US$185", R.drawable.ic_blackshoes),
            BuyData("Nike Air Force 1 '07", "US$115", R.drawable.ic_whiteshoes)
        )
    }
}