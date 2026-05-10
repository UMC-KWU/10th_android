package com.neouul.umc10android.week06.presentation.fragment.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.neouul.umc10android.week06.R
import com.neouul.umc10android.week06.core.hideLoading
import com.neouul.umc10android.week06.core.showLoading
import com.neouul.umc10android.week06.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        val productId = args.productId
        viewModel.loadProduct(productId)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnWishlist.setOnClickListener {
            viewModel.toggleWish()
        }

        observeUiState()
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect { state ->
                        state.product?.let {
                            binding.tvTopTitle.text = it.name
                            binding.tvName.text = it.name
                            binding.tvCategory.text = it.category
                            binding.tvPrice.text = it.price
                            binding.tvDescription.text = it.detailDescription.ifEmpty { it.description }
                            updateWishButtonState(it.isWished)
                        }
                        if (state.isLoading) showLoading() else hideLoading()
                    }
                }
                launch {
                    viewModel.wishEvent.collect { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun updateWishButtonState(isWished: Boolean) {
        if (isWished) {
            binding.btnWishlist.text = "위시리스트 삭제"
            binding.btnWishlist.setIconResource(R.drawable.ic_heart_full)
        } else {
            binding.btnWishlist.text = "위시리스트 추가"
            binding.btnWishlist.setIconResource(R.drawable.ic_heart_empty)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
