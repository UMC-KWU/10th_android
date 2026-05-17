package com.example.umc_week07.ui.navigation

import androidx.annotation.DrawableRes
import com.example.umc_week07.R

sealed class Screen(val route: String, val title: String, @DrawableRes val icon: Int) {
    object Home : Screen("home", "홈", R.drawable.ic_housesimple)
    object Shop : Screen("shop", "구매하기", R.drawable.ic_listmagnifyingglass)
    object Wishlist : Screen("wishlist", "위시리스트", R.drawable.ic_heartstraight)
    object Cart : Screen("cart", "장바구니", R.drawable.ic_bagsimple)
    object Profile : Screen("profile", "프로필", R.drawable.ic_user)
}

val bottomNavItems = listOf(
    Screen.Home,
    Screen.Shop,
    Screen.Wishlist,
    Screen.Cart,
    Screen.Profile
)