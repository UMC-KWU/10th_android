package com.neouul.umc10android.week04.presentation.fragment.wish

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.neouul.umc10android.week04.NavGraphDirections
import com.neouul.umc10android.week04.R
import com.neouul.umc10android.week04.core.MyApplication
import com.neouul.umc10android.week04.databinding.FragmentWishBinding
import com.neouul.umc10android.week04.domain.model.Product
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WishFragment : Fragment(R.layout.fragment_wish) {
    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!

    private val wishRepository by lazy {
        (requireActivity().application as MyApplication).container.wishRepository
    }

    private lateinit var wishAdapter: WishAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWishBinding.bind(view)

        setupRecyclerView()
        observeWishlist()
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

    private fun observeWishlist() {
        viewLifecycleOwner.lifecycleScope.launch {
            wishRepository.getWishedProductsFlow().collectLatest { wishedProducts ->
                wishAdapter.updateList(wishedProducts)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}