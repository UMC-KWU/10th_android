package com.example.umc_10th_android_paul_week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc_10th_android_paul_week02.databinding.FragmentWishlistBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        dataStoreManager = DataStoreManager(requireContext())

        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)

        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.wishlistFlow.collectLatest { allProducts ->
                val likedItems = allProducts.filter { it.isLiked }
                val wishlistAdapter = WishlistAdapter(ArrayList(likedItems))
                binding.rvWishlist.adapter = wishlistAdapter
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}