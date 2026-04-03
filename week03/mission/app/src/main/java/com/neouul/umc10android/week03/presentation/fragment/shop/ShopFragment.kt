package com.neouul.umc10android.week03.presentation.fragment.shop

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.neouul.umc10android.week03.R
import com.neouul.umc10android.week03.databinding.FragmentShopBinding
import com.neouul.umc10android.week03.domain.model.Product

class ShopFragment : Fragment(R.layout.fragment_shop) {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShopBinding.bind(view)

        // 1. 표시할 데이터 목록 생성
        val productList = mutableListOf<Product>(
            Product(
                id = 1L,
                name = "Nike Everyday Plus Cushioned",
                description = "Crew Socks (6 Pairs)",
                detailDescription = "Extra cushioning under the heel and forefoot.",
                category = "Training & Library",
                colorNumber = 1,
                price = "US\$22",
                img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/76a91f58-00a8-477c-a458-197e42d76816/everyday-plus-cushioned-training-crew-socks-6-pairs-9D6v6v.png",
                isBestSeller = true
            ),
            Product(
                id = 2L,
                name = "Nike Air Max 270",
                description = "Men's Shoes",
                detailDescription = "The first lifestyle Air Max brings you style, comfort and big attitude.",
                category = "Lifestyle",
                colorNumber = 3,
                price = "US\$160",
                img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/awwp9bhsqy0dt9gh966n/air-max-270-mens-shoes-Kpj94z.png"
            ),
            Product(
                id = 3L,
                name = "Nike Air Force 1 '07",
                description = "Men's Shoes",
                detailDescription = "The radiance lives on in the Nike Air Force 1 '07.",
                category = "Lifestyle",
                colorNumber = 2,
                price = "US\$115",
                img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/b7d9211c-26e7-431a-ac24-b0540fb3c00f/air-force-1-07-mens-shoes-jps0P8.png",
                isWished = true
            ),
            Product(
                id = 4L,
                name = "Nike Dunk Low Retro",
                description = "Men's Shoes",
                detailDescription = "Created for the hardwood but taken to the streets.",
                category = "Lifestyle",
                colorNumber = 5,
                price = "US\$115",
                img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/g16y8g0i70f0f0f0f0f0/dunk-low-retro-mens-shoes-76Kn9F.png"
            ),
            Product(
                id = 5L,
                name = "Nike Pegasus 40",
                description = "Men's Road Running Shoes",
                detailDescription = "A springy ride for every run.",
                category = "Running",
                colorNumber = 7,
                price = "US\$130",
                img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/e66271e1-e170-4cc8-9441-26c92d50e80e/pegasus-40-mens-road-running-shoes-MCZ9Hz.png"
            ),
            Product(
                id = 6L,
                name = "Nike Blazer Mid '77 Vintage",
                description = "Men's Shoes",
                detailDescription = "Vintage style, modern comfort.",
                category = "Lifestyle",
                colorNumber = 2,
                price = "US\$105",
                img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/60451e06-5f73-45f8-842e-9d261e47f526/blazer-mid-77-vintage-mens-shoes-8N6R5v.png"
            )
        )

        // 2. 어댑터 인스턴스 생성 및 RecyclerView에 연결
        val adapter = ShopAdapter(
            productList,
            onVisitClicked = { product ->
                findNavController().navigate(R.id.action_global_to_detailFragment)
            })

        // 3. 어댑터 연결 및 LayoutManager 설정
        binding.shopRecyclerview.adapter = adapter
        binding.shopRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}