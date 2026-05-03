package com.example.week03_taro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.week03_taro.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            ProductDataStore.seedIfEmpty(applicationContext)
        }

        initBottomNavigation()

        if (savedInstanceState == null) {
            binding.bottomNav.selectedItemId = R.id.menu_home
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    changeFragment(HomeFragment())
                    true
                }

                R.id.menu_shop -> {
                    changeFragment(ShopFragment())
                    true
                }

                R.id.menu_wishlist -> {
                    changeFragment(WishlistFragment())
                    true
                }

                R.id.menu_cart -> {
                    changeFragment(CartFragment())
                    true
                }

                R.id.menu_profile -> {
                    changeFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }

    fun moveToTab(tabId: Int) {
        binding.bottomNav.selectedItemId = tabId
    }
}