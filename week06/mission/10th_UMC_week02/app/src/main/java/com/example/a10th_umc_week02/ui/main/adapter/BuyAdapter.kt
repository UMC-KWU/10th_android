package com.example.a10th_umc_week02.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a10th_umc_week02.R
import com.example.a10th_umc_week02.data.model.BuyData
import com.example.a10th_umc_week02.databinding.ItemBuyBinding

class BuyAdapter(
    private var productList: List<BuyData>,
    private val onHeartClicked: (BuyData) -> Unit = {},  // 기본값 추가 (BasketFragment에서 사용 안 함)
    private val onItemClicked: (BuyData) -> Unit = {}    // 기본값 추가
) : RecyclerView.Adapter<BuyAdapter.BuyViewHolder>() {

    fun updateData(newList: List<BuyData>) {
        this.productList = newList
        notifyDataSetChanged()
    }

    inner class BuyViewHolder(val binding: ItemBuyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: BuyData) {
            binding.tvProduct3.setImageResource(product.image)
            binding.tvProductName3.text = product.name
            binding.tvProductPrice3.text = product.price

            if (product.heart) {
                binding.heart.setImageResource(R.drawable.ic_redheart)
            } else {
                binding.heart.setImageResource(R.drawable.ic_heart)
            }

            binding.heart.setOnClickListener { onHeartClicked(product) }
            binding.root.setOnClickListener { onItemClicked(product) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        val binding = ItemBuyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}
