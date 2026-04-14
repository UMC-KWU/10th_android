package com.example.umc_10th_android_paul_week02

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // 앱을 처음 켰을 때 나올 첫 화면 설정 (홈 화면)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // 하단 바 버튼을 눌렀을 때 동작 설정
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_shop -> {
                    replaceFragment(ShopFragment())
                    true
                }
                R.id.nav_wish -> {
                    replaceFragment(WishlistFragment())
                    true
                }
                R.id.nav_cart -> {
                    replaceFragment(CartFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    // 화면(Fragment)을 교체하는 함수 정의
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment) // activity_main.xml의 FrameLayout ID
            .commit()
    }

    fun changeTab(itemId: Int) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = itemId
    }
}