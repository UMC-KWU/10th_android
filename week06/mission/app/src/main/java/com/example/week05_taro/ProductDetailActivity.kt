package com.example.week03_taro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.week03_taro.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageResId = intent.getIntExtra("imageResId", 0)
        val title = intent.getStringExtra("title").orEmpty()
        val subtitle = intent.getStringExtra("subtitle").orEmpty()
        val price = intent.getStringExtra("price").orEmpty()
        val description = intent.getStringExtra("description").orEmpty()
        val shownColor = intent.getStringExtra("shownColor").orEmpty()
        val styleCode = intent.getStringExtra("styleCode").orEmpty()

        if (imageResId != 0) {
            binding.ivDetailProduct.setImageResource(imageResId)
        }

        binding.tvDetailCategory.text = subtitle
        binding.tvDetailTitle.text = title
        binding.tvDetailPrice.text = price
        binding.tvDetailDescription.text = description
        binding.tvDetailShown.text = "• Shown: $shownColor"
        binding.tvDetailStyle.text = "• Style: $styleCode"

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}