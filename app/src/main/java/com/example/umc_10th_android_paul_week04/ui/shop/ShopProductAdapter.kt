package com.example.umc_10th_android_paul_week04.ui.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_10th_android_paul_week04.R
import com.example.umc_10th_android_paul_week04.data.model.Product
import com.example.umc_10th_android_paul_week04.databinding.ItemShopProductBinding

class ShopProductAdapter(
    private var productList: List<Product>,
    private val onLikeClick: (Product) -> Unit
) : RecyclerView.Adapter<ShopProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemShopProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShopProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        holder.binding.ivProduct.setImageResource(product.imageRes)
        holder.binding.tvTitle.text = product.title
        holder.binding.tvDescription.text = product.description
        holder.binding.tvPrice.text = product.price

        updateHeartIcon(holder, product.isLiked)

        holder.binding.ivHeart.setOnClickListener {
            onLikeClick(product)
        }
    }

    private fun updateHeartIcon(holder: ViewHolder, isLiked: Boolean) {
        if (isLiked) {
            holder.binding.ivHeart.setImageResource(R.drawable.ic_heart_filled)
        } else {
            holder.binding.ivHeart.setImageResource(R.drawable.ic_heart_empty)
        }
    }

    override fun getItemCount(): Int = productList.size

    fun updateData(newList: List<Product>) {
        this.productList = newList
        notifyDataSetChanged()
    }
}