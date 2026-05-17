package com.example.umc_10th_android_paul_week02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc_10th_android_paul_week02.databinding.FragmentShopBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    // 데이터스토어 매니저 선언
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)

        // 뷰페이저 어댑터 연결
        val shopPagerAdapter = ShopPagerAdapter(this)
        binding.vpShop.adapter = shopPagerAdapter

        // 탭 레이아웃과 뷰페이저 연결
        TabLayoutMediator(binding.tlShop, binding.vpShop) { tab, position ->
            tab.text = when (position) {
                0 -> "전체"
                1 -> "Tops & T-Shirts"
                else -> "sale"
            }
        }.attach()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}