package com.example.week03_taro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.week03_taro.databinding.ItemWishlistProductBinding

class WishlistProductAdapter(
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<WishlistProductAdapter.WishlistProductViewHolder>() {

    private val productList = mutableListOf<Product>()

    inner class WishlistProductViewHolder(
        private val binding: ItemWishlistProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.ivWishlistProduct.setImageResource(product.imageResId)
            binding.tvWishlistProductName.text = product.title

            binding.tvWishlistProductSubtitle.isVisible = product.subtitle.isNotBlank()
            binding.tvWishlistProductSubtitle.text = product.subtitle

            binding.tvWishlistProductColors.isVisible = product.colorCount.isNotBlank()
            binding.tvWishlistProductColors.text = product.colorCount

            binding.tvWishlistProductPrice.text = product.price

            binding.root.setOnClickListener {
                onItemClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistProductViewHolder {
        val binding = ItemWishlistProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WishlistProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun submitList(newList: List<Product>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }
}