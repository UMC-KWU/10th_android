package com.neouul.umc10android.week04.presentation.fragment.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.neouul.umc10android.week04.R
import com.neouul.umc10android.week04.core.MyApplication
import com.neouul.umc10android.week04.core.hideLoading
import com.neouul.umc10android.week04.core.showLoading
import com.neouul.umc10android.week04.databinding.FragmentDetailBinding
import com.neouul.umc10android.week04.domain.model.Product
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private var currentProduct: Product? = null

    private val productRepository by lazy {
        (requireActivity().application as MyApplication).container.productRepository
    }
    private val wishRepository by lazy {
        (requireActivity().application as MyApplication).container.wishRepository
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        val productId = args.productId
        loadProduct(productId)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnWishlist.setOnClickListener {
            toggleWish()
        }
    }

    private fun loadProduct(productId: Long) {
        viewLifecycleOwner.lifecycleScope.launch {
            showLoading()
            delay(500)
            val products = productRepository.getTotalProducts().first()
            currentProduct = products.find { it.id == productId }
            updateWishButtonState()
            hideLoading()
        }
    }

    private fun toggleWish() {
        val product = currentProduct ?: return
        val newWishState = !product.isWished
        val updatedProduct = product.copy(isWished = newWishState)

        viewLifecycleOwner.lifecycleScope.launch {
            showLoading()
            delay(500)
            productRepository.updateTotalProduct(updatedProduct)
            if (newWishState) {
                wishRepository.addWishedProduct(updatedProduct)
                Toast.makeText(requireContext(), "위시리스트에 추가되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                wishRepository.removeWishedProduct(updatedProduct)
                Toast.makeText(requireContext(), "위시리스트에서 제거되었습니다.", Toast.LENGTH_SHORT).show()
            }
            currentProduct = updatedProduct
            updateWishButtonState()
            hideLoading()
        }
    }

    private fun updateWishButtonState() {
        val isWished = currentProduct?.isWished ?: false
        if (isWished) {
            binding.btnWishlist.text = "위시리스트 삭제"
            binding.btnWishlist.setIconResource(R.drawable.ic_heart_straight)
        } else {
            binding.btnWishlist.text = "위시리스트 추가"
            binding.btnWishlist.setIconResource(R.drawable.ic_heart_straight)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
