package com.example.taro.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.taro.R
import com.example.taro.databinding.ActivityMainBinding
import com.example.taro.ui.cart.CartFragment
import com.example.taro.ui.home.HomeFragment
import com.example.taro.ui.profile.ProfileFragment
import com.example.taro.ui.shop.ShopFragment
import com.example.taro.ui.wishlist.WishlistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.seedProductsIfEmpty()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_shop -> {
                    replaceFragment(ShopFragment())
                    true
                }

                R.id.menu_wishlist -> {
                    replaceFragment(WishlistFragment())
                    true
                }

                R.id.menu_cart -> {
                    replaceFragment(CartFragment())
                    true
                }

                R.id.menu_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    fun moveToTab(menuId: Int) {
        binding.bottomNavigationView.selectedItemId = menuId
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }
}