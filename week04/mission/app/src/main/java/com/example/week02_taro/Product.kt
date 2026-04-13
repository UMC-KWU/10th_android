package com.example.week03_taro

data class Product(
    val id: String,
    val imageResId: Int,
    val title: String,
    val subtitle: String = "",
    val price: String,
    val colorCount: String = "",
    val badge: String = "",
    val description: String = "",
    val shownColor: String = "",
    val styleCode: String = "",
    val isFavorite: Boolean = false,
    val isShownInHome: Boolean = false,
    val isShownInShop: Boolean = false
)