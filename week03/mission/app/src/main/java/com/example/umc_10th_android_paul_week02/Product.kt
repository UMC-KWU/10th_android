package com.example.umc_10th_android_paul_week02

data class Product(
    val imageRes: Int,
    val title: String,
    val description: String,
    val price: String,
    var isLiked: Boolean = false
)