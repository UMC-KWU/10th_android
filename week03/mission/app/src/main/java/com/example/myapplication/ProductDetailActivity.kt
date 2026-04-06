package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getIntExtra("image", 0)
        val name = intent.getStringExtra("name") ?: ""
        val category = intent.getStringExtra("category") ?: ""
        val price = intent.getStringExtra("price") ?: ""

        binding.ivProduct.setImageResource(image)
        binding.tvProductTitle.text = name
        binding.tvName.text = name
        binding.tvCategory.text = category
        binding.tvPrice.text = price

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnWishlist.setOnClickListener {
            // 위시리스트 토글 (나중에 구현)
        }
    }
}