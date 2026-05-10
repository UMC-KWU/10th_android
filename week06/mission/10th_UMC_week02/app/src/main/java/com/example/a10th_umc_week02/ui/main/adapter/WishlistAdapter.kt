package com.example.a10th_umc_week02.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a10th_umc_week02.data.model.BuyData
import com.example.a10th_umc_week02.databinding.ItemWishlistBinding

class WishlistAdapter(
    private var productList: List<BuyData>,
    private val onItemClick: (BuyData) -> Unit
) : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    fun updateData(newList: List<BuyData>) {
        this.productList = newList
        notifyDataSetChanged()
    }

    inner class WishlistViewHolder(val binding: ItemWishlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: BuyData) {
            binding.tvProduct2.setImageResource(product.image)
            binding.tvProductName2.text = product.name
            binding.tvProductPrice2.text = product.price

            binding.root.setOnClickListener { onItemClick(product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val binding = ItemWishlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WishlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}
