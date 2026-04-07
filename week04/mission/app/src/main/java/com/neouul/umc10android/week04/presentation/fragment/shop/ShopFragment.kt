package com.neouul.umc10android.week04.presentation.fragment.shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.neouul.umc10android.week04.NavGraphDirections
import com.neouul.umc10android.week04.R
import com.neouul.umc10android.week04.core.MyApplication
import com.neouul.umc10android.week04.databinding.FragmentShopBinding
import com.neouul.umc10android.week04.domain.model.Product
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ShopFragment : Fragment(R.layout.fragment_shop) {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private val productRepository by lazy {
        (requireActivity().application as MyApplication).container.productRepository
    }
    private val wishRepository by lazy {
        (requireActivity().application as MyApplication).container.wishRepository
    }

    private lateinit var shopAdapter: ShopAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShopBinding.bind(view)

        setupRecyclerView()
        observeProducts()
    }

    private fun setupRecyclerView() {
        shopAdapter = ShopAdapter(
            mutableListOf(),
            onVisitClicked = { product ->
                val action = NavGraphDirections.actionGlobalToDetailFragment(product.id)
                findNavController().navigate(action)
            },
            onWishClicked = { product ->
                toggleWish(product)
            }
        )
        binding.shopRecyclerview.adapter = shopAdapter
        binding.shopRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun observeProducts() {
        viewLifecycleOwner.lifecycleScope.launch {
            productRepository.getTotalProducts().collectLatest { products ->
                shopAdapter.updateList(products)
            }
        }
    }

    private fun toggleWish(product: Product) {
        val newWishState = !product.isWished
        val updatedProduct = product.copy(isWished = newWishState)

        viewLifecycleOwner.lifecycleScope.launch {
            productRepository.updateTotalProduct(updatedProduct)
            if (newWishState) {
                wishRepository.addWishedProduct(updatedProduct)
            } else {
                wishRepository.removeWishedProduct(updatedProduct)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}