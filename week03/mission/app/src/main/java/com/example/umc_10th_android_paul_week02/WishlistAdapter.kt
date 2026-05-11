package com.example.umc_10th_android_paul_week02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_10th_android_paul_week02.databinding.ItemWishlistProductBinding

class WishlistAdapter(private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemWishlistProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWishlistProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.ivProduct.setImageResource(product.imageRes)
        holder.binding.tvTitle.text = product.title
        holder.binding.tvDescription.text = product.description
        holder.binding.tvPrice.text = product.price
    }

    override fun getItemCount(): Int = productList.size
}