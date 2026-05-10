package com.neouul.umc10android.week06.presentation.fragment.shop_tap0

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.neouul.umc10android.week06.NavGraphDirections
import com.neouul.umc10android.week06.R
import com.neouul.umc10android.week06.databinding.FragmentShopTap0Binding
import com.neouul.umc10android.week06.presentation.fragment.shop.ShopAdapter
import com.neouul.umc10android.week06.presentation.fragment.shop.ShopViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShopTap0Fragment : Fragment(R.layout.fragment_shop_tap0) {

    private var _binding: FragmentShopTap0Binding? = null
    private val binding get() = _binding!!

    private val viewModel: ShopViewModel by viewModels()

    private lateinit var shopAdapter: ShopAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShopTap0Binding.bind(view)

        setupRecyclerView()
        observeUiState()
    }

    private fun setupRecyclerView() {
        shopAdapter = ShopAdapter(
            mutableListOf(),
            onVisitClicked = { product ->
                // 부모(MainActivity)의 NavController를 사용하여 DetailFragment로 이동
                val action = NavGraphDirections.actionGlobalToDetailFragment(product.id)
                Navigation.findNavController(requireActivity(), R.id.main_nav_host_fragment).navigate(action)
            },
            onWishClicked = { product ->
                viewModel.toggleWish(product)
            }
        )
        binding.shopRecyclerview.adapter = shopAdapter
        binding.shopRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    shopAdapter.updateList(state.allProducts)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}