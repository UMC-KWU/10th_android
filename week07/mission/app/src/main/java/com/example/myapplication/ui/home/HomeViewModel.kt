package com.example.myapplication.ui.home

import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val products = listOf(
        Product(R.drawable.product_1, "Air Jordan XXXVI", "", "", price = "US\$185"),
        Product(R.drawable.product_2, "Nike Air Force 1 '07", "", "", price = "US\$115")
    )
}
