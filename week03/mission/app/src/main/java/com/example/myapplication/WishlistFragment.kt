package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment() {
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wishlistedProducts = WishlistManager.getWishlist()

        binding.rvWishlist.layoutManager = GridLayoutManager(context, 2)
        binding.rvWishlist.adapter = BuyProductAdapter(wishlistedProducts) { product ->
            // 상세 화면 이동 (나중에 구현)
        }
        binding.rvWishlist.adapter = BuyProductAdapter(wishlistedProducts) { product ->
            val intent = android.content.Intent(context, ProductDetailActivity::class.java).apply {
                putExtra("image", product.image)
                putExtra("name", product.name)
                putExtra("category", product.category)
                putExtra("price", product.price)
            }
            startActivity(intent)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}