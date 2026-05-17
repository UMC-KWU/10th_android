package com.example.umc_10th_android_paul_week02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc_10th_android_paul_week02.databinding.FragmentTotalBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TotalFragment : Fragment() {

    private var _binding: FragmentTotalBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTotalBinding.inflate(inflater, container, false)
        dataStoreManager = DataStoreManager(requireContext())

        val shopProductList = arrayListOf(
            Product(R.drawable.socks_nike, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)\n5 Colours", "US$10", false),
            Product(R.drawable.socks_nike_elite, "Nike Elite Crew", "Basketball Socks\n7 Colours", "US$16", false),
            Product(R.drawable.shoe_airforce_01, "Nike Air Force 1 '07", "Women's Shoes\n5 Colours", "US$115", false),
            Product(R.drawable.shoe_jordan_01, "Jordan Essentials", "Men's Shoes\n2 Colours", "US$115", false)
        )

        val shopAdapter = ShopProductAdapter(shopProductList)
        shopAdapter.dataStoreManager = dataStoreManager

        binding.rvShopProduct.adapter = shopAdapter
        binding.rvShopProduct.layoutManager = GridLayoutManager(requireContext(), 2)

        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.wishlistFlow.collectLatest { savedList ->
                if (savedList.isNotEmpty()) {
                    shopProductList.clear()
                    shopProductList.addAll(savedList)
                    shopAdapter.notifyDataSetChanged()
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}