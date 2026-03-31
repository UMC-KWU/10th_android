package com.example.week03_taro

data class Product(
    val imageResId: Int,
    val title: String,
    val subtitle: String = "",
    val price: String
)