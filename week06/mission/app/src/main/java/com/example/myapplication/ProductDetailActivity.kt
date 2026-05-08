package com.example.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.databinding.ActivityProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var product: Product
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getIntExtra("image", 0)
        val name = intent.getStringExtra("name") ?: ""
        val category = intent.getStringExtra("category") ?: ""
        val price = intent.getStringExtra("price") ?: ""
        val colors = intent.getStringExtra("colors") ?: ""

        product = Product(image, name, category, colors, price)

        binding.ivProduct.setImageResource(image)
        binding.tvProductTitle.text = name
        binding.tvName.text = name
        binding.tvCategory.text = category
        binding.tvPrice.text = price

        viewModel.checkWishlist(product)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isWishlisted.collect { isWishlisted ->
                    binding.btnWishlist.isSelected = isWishlisted
                }
            }
        }

        binding.btnBack.setOnClickListener { finish() }
        binding.btnWishlist.setOnClickListener { viewModel.toggle(product) }
    }
}
