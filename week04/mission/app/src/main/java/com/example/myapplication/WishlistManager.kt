package com.example.myapplication

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object WishlistManager {
    private val wishlist = mutableListOf<Product>()
    private lateinit var dataStore: WishlistDataStore

    fun init(context: Context){
        dataStore = WishlistDataStore(context)
        runBlocking {
            val saved = dataStore.wishlistFlow.first()
            wishlist.clear()
            wishlist.addAll(saved)
        }
    }

    fun toggle(product: Product) {
        val existing = wishlist.find { it.name == product.name }
        if (existing != null) {
            wishlist.remove(existing)
            product.isWishlisted = false
        } else {
            product.isWishlisted = true
            wishlist.add(product)
        }
        save()
    }

    fun getWishlist(): List<Product> = wishlist.toList()

    fun isWishlisted(product: Product): Boolean{
        return wishlist.any {it.name == product.name}
    }
    private fun save(){
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.saveWishlist(wishlist)
        }
    }
}