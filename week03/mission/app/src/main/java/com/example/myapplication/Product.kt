package com.example.myapplication

data class Product(
    val image: Int,
    val name: String,
    val category: String,
    val colors: String,
    val price: String,
    val isBestSeller: Boolean = false
)