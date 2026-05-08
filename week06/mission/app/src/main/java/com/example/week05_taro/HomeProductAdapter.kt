package com.example.week03_taro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week03_taro.databinding.ItemHomeProductBinding

class HomeProductAdapter(
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<HomeProductAdapter.HomeProductViewHolder>() {

    private val productList = mutableListOf<Product>()

    inner class HomeProductViewHolder(
        private val binding: ItemHomeProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.ivHomeProduct.setImageResource(product.imageResId)
            binding.tvHomeProductName.text = product.title
            binding.tvHomeProductPrice.text = product.price

            binding.root.setOnClickListener {
                onItemClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductViewHolder {
        val binding = ItemHomeProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun submitList(newList: List<Product>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }
}