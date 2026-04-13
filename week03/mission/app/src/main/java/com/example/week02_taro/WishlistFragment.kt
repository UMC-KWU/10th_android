package com.example.week03_taro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week03_taro.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    private val wishlistProductList = listOf(
        Product(
            imageResId = R.drawable.img_product_mid,
            title = "Air Jordan 1 Mid",
            price = "US$125",
            description = "Inspired by the original AJ1, this mid-top edition maintains the iconic look you love.",
            shownColor = "White",
            styleCode = "DQ8426-100"
        ),
        Product(
            imageResId = R.drawable.img_product_socks,
            title = "Nike Everyday Plus Cushioned",
            subtitle = "Training Ankle Socks (6 Pairs)",
            colorCount = "5 Colours",
            price = "US$10",
            description = "The Nike Everyday Plus Cushioned Socks bring comfort to your workout with extra cushioning under the heel and forefoot and a snug, supportive arch band.",
            shownColor = "Multi-Color",
            styleCode = "SX6897-965"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvWishlistProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = WishlistProductAdapter(wishlistProductList) { product ->
                moveToDetail(product)
            }
            setHasFixedSize(true)
        }
    }

    private fun moveToDetail(product: Product) {
        val intent = Intent(requireContext(), ProductDetailActivity::class.java).apply {
            putExtra("imageResId", product.imageResId)
            putExtra("title", product.title)
            putExtra("subtitle", product.subtitle)
            putExtra("price", product.price)
            putExtra("colorCount", product.colorCount)
            putExtra("badge", product.badge)
            putExtra("description", product.description)
            putExtra("shownColor", product.shownColor)
            putExtra("styleCode", product.styleCode)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}