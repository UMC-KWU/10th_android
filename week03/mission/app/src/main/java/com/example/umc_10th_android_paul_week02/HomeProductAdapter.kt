package com.example.umc_10th_android_paul_week02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_10th_android_paul_week02.databinding.ItemHomeProductBinding

class HomeProductAdapter(private val productList: ArrayList<HomeProduct>) :
    RecyclerView.Adapter<HomeProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemHomeProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        holder.binding.ivProduct.setImageResource(product.imageRes)
        holder.binding.tvTitle.text = product.title
        holder.binding.tvPrice.text = product.price
    }

    override fun getItemCount(): Int = productList.size
}