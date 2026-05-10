package com.neouul.umc10android.week06.presentation.fragment.wish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.neouul.umc10android.week06.NavGraphDirections
import com.neouul.umc10android.week06.R
import com.neouul.umc10android.week06.databinding.FragmentWishBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishFragment : Fragment(R.layout.fragment_wish) {
    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WishViewModel by viewModels()

    private lateinit var wishAdapter: WishAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWishBinding.bind(view)

        setupRecyclerView()
        observeUiState()
    }

    private fun setupRecyclerView() {
        wishAdapter = WishAdapter(
            mutableListOf(),
            onVisitClicked = { product ->
                val action = NavGraphDirections.actionGlobalToDetailFragment(product.id)
                findNavController().navigate(action)
            }
        )
        binding.wishRecyclerview.adapter = wishAdapter
        binding.wishRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    wishAdapter.updateList(state.wishedProducts)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}