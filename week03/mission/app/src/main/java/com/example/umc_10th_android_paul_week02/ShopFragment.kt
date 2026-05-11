package com.example.umc_10th_android_paul_week02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc_10th_android_paul_week02.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)

        // 1. 제공해주신 4개 이미지로 상품 데이터 리스트 생성
        val shopProductList = arrayListOf(
            Product(R.drawable.socks_nike, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)\n5 Colours", "US$10", false),
            Product(R.drawable.socks_nike_elite, "Nike Elite Crew", "Basketball Socks\n7 Colours", "US$16", false),
            Product(R.drawable.shoe_airforce_01, "Nike Air Force 1 '07", "Women's Shoes\n5 Colours", "US$115", false),
            Product(R.drawable.shoe_jordan_01, "Jordan ENike Air Force \n1 '07ssentials", "Men's Shoes\n2 Colours", "US$115", false)
        )

        // 2. 어댑터 생성 및 데이터 연결
        val shopAdapter = ShopProductAdapter(shopProductList)
        binding.rvShopProduct.adapter = shopAdapter
        binding.rvShopProduct.layoutManager = GridLayoutManager(requireContext(), 2)

        // 3. GridLayoutManager
        binding.rvShopProduct.layoutManager = GridLayoutManager(requireContext(), 2)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}