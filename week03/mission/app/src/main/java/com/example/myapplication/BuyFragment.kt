package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentBuyBinding
import com.google.android.material.tabs.TabLayout

class BuyFragment : Fragment() {

    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!

    private val allProducts = listOf(
        Product(R.mipmap.product_1, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", "5 Colours", "US\$10"),
        Product(R.mipmap.product_2, "Nike Elite Crew", "Basketball Socks", "7 Colours", "US\$16"),
        Product(R.mipmap.product_3, "Nike Air Force 1 '07", "Women's Shoes", "5 Colours", "US\$115", isBestSeller = true),
        Product(R.mipmap.product_4, "Jordan ENike Air Force 1 '07essentials", "Men's Shoes", "2 Colours", "US\$115", isBestSeller = true)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabs()
        setupRecyclerView(allProducts)
    }

    private fun setupTabs() {
        val tabs = listOf("전체", "Tops & T-Shirts", "Shoes")
        tabs.forEach { binding.tabLayout.addTab(binding.tabLayout.newTab().setText(it)) }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val filtered = when (tab?.position) {
                    1 -> allProducts.filter { it.category.contains("Socks") }
                    2 -> allProducts.filter { it.category.contains("Shoes") }
                    else -> allProducts
                }
                setupRecyclerView(filtered)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setupRecyclerView(products: List<Product>) {
        binding.rvProducts.layoutManager = GridLayoutManager(context, 2)
        binding.rvProducts.adapter = BuyProductAdapter(products) { product ->
            // 상세 화면 이동 (나중에 구현)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}