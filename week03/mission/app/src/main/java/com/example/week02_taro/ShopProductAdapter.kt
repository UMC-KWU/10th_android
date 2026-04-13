package com.example.week03_taro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.week03_taro.databinding.ItemShopProductBinding

class ShopProductAdapter(
    private val productList: MutableList<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ShopProductAdapter.ShopProductViewHolder>() {

    inner class ShopProductViewHolder(
        private val binding: ItemShopProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.ivShopProduct.setImageResource(product.imageResId)
            binding.tvShopProductName.text = product.title

            binding.tvShopProductSubtitle.isVisible = product.subtitle.isNotBlank()
            binding.tvShopProductSubtitle.text = product.subtitle

            binding.tvShopProductColors.isVisible = product.colorCount.isNotBlank()
            binding.tvShopProductColors.text = product.colorCount

            binding.tvShopProductPrice.text = product.price

            binding.tvShopBadge.isVisible = product.badge.isNotBlank()
            binding.tvShopBadge.text = product.badge

            binding.tvShopHeart.text = if (product.isFavorite) "♥" else "♡"

            binding.root.setOnClickListener {
                onItemClick(product)
            }

            binding.tvShopHeart.setOnClickListener {
                product.isFavorite = !product.isFavorite
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopProductViewHolder {
        val binding = ItemShopProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShopProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}