package com.example.umc_10th_android_paul_week02

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ShopPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TotalFragment()       // 1번째 탭: 기존 리사이클러뷰 화면
            1 -> TopTshirtsFragment()  // 2번째 탭: 빈 화면
            else -> SaleFragment()     // 3번째 탭: 빈 화면
        }
    }
}