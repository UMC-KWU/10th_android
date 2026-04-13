package com.example.week03_taro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week03_taro.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private val shopProductList = mutableListOf(
        Product(
            imageResId = R.drawable.img_product_socks,
            title = "Nike Everyday Plus Cushioned",
            subtitle = "Training Ankle Socks (6 Pairs)",
            colorCount = "5 Colours",
            price = "US$10",
            description = "The Nike Everyday Plus Cushioned Socks bring comfort to your workout with extra cushioning under the heel and forefoot and a snug, supportive arch band. Sweat-wicking power and breathability up top help keep your feet dry and cool to help push you through that extra set.",
            shownColor = "Multi-Color",
            styleCode = "SX6897-965",
            isFavorite = true
        ),
        Product(
            imageResId = R.drawable.img_product_socks2,
            title = "Nike Elite Crew",
            subtitle = "Basketball Socks",
            colorCount = "7 Colours",
            price = "US$16",
            description = "Basketball socks designed for support and comfort during play.",
            shownColor = "White/Black",
            styleCode = "DX1234-100"
        ),
        Product(
            imageResId = R.drawable.img_product_airforce,
            title = "Nike Air Force 1 '07",
            subtitle = "Women's Shoes",
            colorCount = "5 Colours",
            price = "US$115",
            badge = "BestSeller",
            description = "The radiance lives on in the Nike Air Force 1 '07, the basketball original that puts a fresh spin on what you know best.",
            shownColor = "White",
            styleCode = "DD8959-100"
        ),
        Product(
            imageResId = R.drawable.img_product_airforce2,
            title = "Jordan ENike Air Force 1 '07sentials",
            subtitle = "Men's Shoes",
            colorCount = "2 Colours",
            price = "US$115",
            badge = "BestSeller",
            description = "Classic court style with premium materials and everyday comfort.",
            shownColor = "White/Gray",
            styleCode = "HF0001-101"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvShopProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = ShopProductAdapter(shopProductList) { product ->
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