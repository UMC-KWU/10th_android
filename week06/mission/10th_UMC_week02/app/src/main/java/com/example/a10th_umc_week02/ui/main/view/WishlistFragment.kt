package com.example.a10th_umc_week02.ui.main.view

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
import com.example.a10th_umc_week02.data.model.BuyData
import com.example.a10th_umc_week02.databinding.FragmentWishlistBinding
import com.example.a10th_umc_week02.ui.main.adapter.WishlistAdapter
import com.example.a10th_umc_week02.ui.main.viewmodel.WishlistViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WishlistViewModel by viewModels()
    private lateinit var wishAdapter: WishlistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wishAdapter = WishlistAdapter(emptyList()) { product ->
            viewModel.removeItem(product)
        }

        binding.WishlistRecyclerview.apply {
            adapter = wishAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wishList.collect { newList: List<BuyData> ->
                    wishAdapter.updateData(newList)
                }
            }
        }

        viewModel.loadWishList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}