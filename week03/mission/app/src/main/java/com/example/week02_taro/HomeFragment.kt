package com.example.week03_taro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week03_taro.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeProductList = listOf(
        Product(
            imageResId = R.drawable.img_product_jordan_black,
            title = "Air Jordan XXXVI",
            price = "US$185"
        ),
        Product(
            imageResId = R.drawable.img_product_jordan_white,
            title = "Air Jordan 1 Low",
            price = "US$145"
        ),
        Product(
            imageResId = R.drawable.img_product_mid,
            title = "Air Jordan 1 Mid",
            price = "US$125"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHomeProducts.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = HomeProductAdapter(homeProductList) { product ->
                moveToDetail(product)
            }
            setHasFixedSize(true)
        }
    }

    private fun moveToDetail(product: Product) {
        val intent = Intent(requireContext(), ProductDetailActivity::class.java).apply {
            putExtra("imageResId", product.imageResId)
            putExtra("title", product.title)
            putExtra("subtitle", product.subtitle)
            putExtra("price", product.price)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}