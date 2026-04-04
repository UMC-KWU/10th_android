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

            binding.root.setOnClickListener {
                onItemClick(product)
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