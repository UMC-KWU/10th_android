package com.example.week03_taro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.week03_taro.databinding.ItemShopProductBinding

class ShopProductAdapter(
    private val onItemClick: (Product) -> Unit,
    private val onHeartClick: (Product) -> Unit
) : RecyclerView.Adapter<ShopProductAdapter.ShopProductViewHolder>() {

    private val productList = mutableListOf<Product>()

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

            binding.ivShopHeart.setImageResource(
                if (product.isFavorite) R.drawable.ic_heart_on
                else R.drawable.ic_heart_off
            )

            binding.root.setOnClickListener {
                onItemClick(product)
            }

            binding.ivShopHeart.setOnClickListener {
                onHeartClick(product)
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

    fun submitList(newList: List<Product>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }
}