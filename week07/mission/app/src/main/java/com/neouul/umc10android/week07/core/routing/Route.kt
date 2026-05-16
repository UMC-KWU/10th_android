package com.neouul.umc10android.week07.core.routing

import kotlinx.serialization.Serializable

sealed interface Route {
    // 중첩 그래프
    @Serializable
    data object MainGraph : Route


    // 화면
    @Serializable
    data object Splash : Route

    @Serializable
    data object SignIn : Route

    @Serializable
    data object SignUp : Route


    @Serializable
    data object Main : Route

    @Serializable
    data object Home : Route

    @Serializable
    data object Shop : Route

    @Serializable
    data object Wish : Route

    @Serializable
    data object Cart : Route

    @Serializable
    data object Profile : Route

    @Serializable
    data class ProductDetail(val productId: Long) : Route
}