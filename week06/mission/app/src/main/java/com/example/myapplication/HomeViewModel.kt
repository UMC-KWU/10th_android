package com.example.myapplication

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val products = listOf(
        Product(R.mipmap.product_1, "Air Jordan XXXVI", "", "", price = "US\$185"),
        Product(R.mipmap.product_2, "Nike Air Force 1 '07", "", "", price = "US\$115")
    )
}
