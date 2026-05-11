package com.example.umc_10th_android_paul_week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc_10th_android_paul_week02.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)

        // 더미 데이터 (보내주신 이미지 2개 활용)
        val wishlistItems = arrayListOf(
            Product(R.drawable.socks_nike, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", "US$10"),
            Product(R.drawable.shoe_nike_ankle, "Air Jordan 1 Mid", "", "US$125")
        )

        val wishlistAdapter = WishlistAdapter(wishlistItems)
        binding.rvWishlist.adapter = wishlistAdapter

        // 2열 격자 구조 설정
        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}