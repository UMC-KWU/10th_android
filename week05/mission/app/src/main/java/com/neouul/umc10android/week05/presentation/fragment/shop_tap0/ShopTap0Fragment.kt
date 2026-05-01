package com.neouul.umc10android.week05.presentation.fragment.shop_tap0

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.neouul.umc10android.week05.NavGraphDirections
import com.neouul.umc10android.week05.R
import com.neouul.umc10android.week05.core.MyApplication
import com.neouul.umc10android.week05.databinding.FragmentShopTap0Binding
import com.neouul.umc10android.week05.domain.model.Product
import com.neouul.umc10android.week05.presentation.fragment.shop.ShopAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ShopTap0Fragment : Fragment(R.layout.fragment_shop_tap0) {

    private var _binding: FragmentShopTap0Binding? = null
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
        _binding = FragmentShopTap0Binding.bind(view)

        setupRecyclerView()
        observeProducts()
    }

    private fun setupRecyclerView() {
        shopAdapter = ShopAdapter(
            mutableListOf(),
            onVisitClicked = { product ->
                // 부모(MainActivity)의 NavController를 사용하여 DetailFragment로 이동
                val action = NavGraphDirections.actionGlobalToDetailFragment(product.id)
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(action)
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