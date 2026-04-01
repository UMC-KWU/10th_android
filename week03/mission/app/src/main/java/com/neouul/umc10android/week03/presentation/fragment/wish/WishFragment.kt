package com.neouul.umc10android.week03.presentation.fragment.wish

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.neouul.umc10android.week03.R
import com.neouul.umc10android.week03.databinding.FragmentWishBinding
import com.neouul.umc10android.week03.domain.model.Product

class WishFragment : Fragment(R.layout.fragment_wish) {
    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWishBinding.bind(view)

        // 1. 표시할 데이터 목록 생성
        val productList = mutableListOf<Product>(
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
        val adapter = WishAdapter(
            productList,
            onVisitClicked = { product ->
                Toast.makeText(context, "${product.name}에게 방문하기", Toast.LENGTH_SHORT).show()
            })

        // 3. 어댑터 연결 및 LayoutManager 설정
        binding.wishRecyclerview.adapter = adapter
        binding.wishRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}