package com.example.a10th_umc_week02.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a10th_umc_week02.data.model.HomeData
import com.example.a10th_umc_week02.databinding.ItemHomeBinding
import com.example.a10th_umc_week02.ui.main.Click

class HomeAdapter(
    private var productList: List<HomeData>,
    private val delegate: Click
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    fun updateData(newList: List<HomeData>){
        this.productList = newList
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(home: HomeData) {
            binding.tvProduct.setImageResource(home.image)
            binding.tvProductName.text = home.name
            binding.tvProductPrice.text = home.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            delegate.onClick(product)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}