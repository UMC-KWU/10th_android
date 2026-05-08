package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var wishlistRepository: WishlistRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            wishlistRepository.load()
        }

        setupNavigation()
    }

    private fun setupNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragmentContainer, HomeFragment())
            .commit()
        binding.mainBnv.selectedItemId = R.id.home

        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, HomeFragment())
                        .commit()
                    true
                }
                R.id.buy -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, BuyFragment())
                        .commit()
                    true
                }
                R.id.wishList -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, WishlistFragment())
                        .commit()
                    true
                }
                R.id.bag -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, BagFragment())
                        .commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragmentContainer, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
