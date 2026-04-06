package com.example.myapplication

object WishlistManager {
    private val wishlist = mutableListOf<Product>()

    fun toggle(product: Product) {
        if (wishlist.contains(product)) {
            wishlist.remove(product)
            product.isWishlisted = false
        } else {
            wishlist.add(product)
            product.isWishlisted = true
        }
    }

    fun getWishlist(): List<Product> = wishlist.toList()

    fun isWishlisted(product: Product): Boolean = wishlist.contains(product)
}