package com.example.taro.ui.shop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.taro.data.model.Product
import com.example.taro.databinding.FragmentShopAllBinding
import com.example.taro.ui.detail.ProductDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShopAllFragment : Fragment() {

    private var _binding: FragmentShopAllBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ShopAllViewModel by viewModels()

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

        setupShopRecyclerView()
        collectUiState()
    }

    private fun setupShopRecyclerView() {
        shopAdapter = ShopProductAdapter(
            onItemClick = { product ->
                moveToDetail(product)
            },
            onHeartClick = { product ->
                viewModel.toggleFavorite(product.id)
            }
        )

        binding.rvShopProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = shopAdapter
            setHasFixedSize(true)
        }
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    shopAdapter.submitList(state.products)
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