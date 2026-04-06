package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemBuyProductBinding

class BuyProductAdapter(
    private val products: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<BuyProductAdapter.BuyProductViewHolder>() {

    inner class BuyProductViewHolder(private val binding: ItemBuyProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.ivProduct.setImageResource(product.image)
            binding.tvName.text = product.name
            binding.tvCategory.text = product.category
            binding.tvColors.text = product.colors
            binding.tvPrice.text = product.price

            if (product.isBestSeller) {
                binding.tvBestSeller.visibility = View.VISIBLE
            } else {
                binding.tvBestSeller.visibility = View.GONE
            }
            updateWishlistIcon(product)

            binding.btnWishlist.setOnClickListener {
                WishlistManager.toggle(product)
                updateWishlistIcon(product)
            }

            binding.root.setOnClickListener {
                onItemClick(product)
            }
        }

        private fun updateWishlistIcon(product: Product) {
            if (WishlistManager.isWishlisted(product)) {
                binding.btnWishlist.setImageResource(R.drawable.heartstraight_filled) // 채워진 하트
            } else {
                binding.btnWishlist.setImageResource(R.drawable.heartstraight) // 빈 하트
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyProductViewHolder {
        val binding = ItemBuyProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BuyProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size
}