package com.example.week03_taro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week03_taro.databinding.FragmentShopAllBinding
import kotlinx.coroutines.launch

class ShopAllFragment : Fragment() {

    private var _binding: FragmentShopAllBinding? = null
    private val binding get() = _binding!!

    private lateinit var shopAdapter: ShopProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shopAdapter = ShopProductAdapter(
            onItemClick = { product ->
                moveToDetail(product)
            },
            onHeartClick = { product ->
                viewLifecycleOwner.lifecycleScope.launch {
                    ProductDataStore.toggleFavorite(requireContext(), product.id)
                }
            }
        )

        binding.rvShopProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = shopAdapter
            setHasFixedSize(true)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                ProductDataStore.getShopProducts(requireContext()).collect { products ->
                    shopAdapter.submitList(products)
                }
            }
        }
    }

    private fun moveToDetail(product: Product) {
        val intent = Intent(requireContext(), ProductDetailActivity::class.java).apply {
            putExtra("imageResId", product.imageResId)
            putExtra("title", product.title)
            putExtra("subtitle", product.subtitle)
            putExtra("price", product.price)
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