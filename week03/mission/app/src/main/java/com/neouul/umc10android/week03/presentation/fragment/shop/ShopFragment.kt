package com.neouul.umc10android.week03.presentation.fragment.shop

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
                name = "Nike Everyday Plus Cushioned",
                description = "",
                detailDescription = "",
                category = "",
                colorNumber = 5,
                price = "US\$10",
                img = ""
            ),
            Product(
                name = "Nike Everyday Plus Cushioned2",
                description = "",
                detailDescription = "",
                category = "",
                colorNumber = 5,
                price = "US\$10",
                img = ""
            ),
            Product(
                name = "Nike Everyday Plus Cushioned2",
                description = "",
                detailDescription = "",
                category = "",
                colorNumber = 5,
                price = "US\$10",
                img = ""
            ),
            Product(
                name = "Nike Everyday Plus Cushioned2",
                description = "",
                detailDescription = "",
                category = "",
                colorNumber = 5,
                price = "US\$10",
                img = ""
            ),
        )

        // 2. 어댑터 인스턴스 생성 및 RecyclerView에 연결
        val adapter = ShopAdapter(
            productList,
            onVisitClicked = { product ->
                Toast.makeText(context, "${product.name}에게 방문하기", Toast.LENGTH_SHORT).show()
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