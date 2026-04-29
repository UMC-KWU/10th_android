package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var product: Product

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

        updateWishlistButton()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnWishlist.setOnClickListener {
            WishlistManager.toggle(product)
            updateWishlistButton()
        }
    }

    private fun updateWishlistButton() {
        val isWishlisted = WishlistManager.isWishlisted(product)
        binding.btnWishlist.isSelected = isWishlisted
    }
}
