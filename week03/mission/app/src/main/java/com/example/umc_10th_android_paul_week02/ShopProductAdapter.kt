package com.example.umc_10th_android_paul_week02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_10th_android_paul_week02.databinding.ItemShopProductBinding

class ShopProductAdapter(private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<ShopProductAdapter.ViewHolder>() {

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

        // 데이터 연결
        holder.binding.ivProduct.setImageResource(product.imageRes)
        holder.binding.tvTitle.text = product.title
        holder.binding.tvDescription.text = product.description
        holder.binding.tvPrice.text = product.price

        // 1. 처음 로딩될 때 하트 상태 표시
        updateHeartIcon(holder, product.isLiked)

        // 2. 하트 클릭 시 상태 변경 로직
        holder.binding.ivHeart.setOnClickListener {
            product.isLiked = !product.isLiked // true -> false, false -> true 전환
            updateHeartIcon(holder, product.isLiked)
        }
    }

    // 하트 아이콘 이미지를 교체해주는 함수
    private fun updateHeartIcon(holder: ViewHolder, isLiked: Boolean) {
        if (isLiked) {
            holder.binding.ivHeart.setImageResource(R.drawable.ic_heart_filled)
        } else {
            holder.binding.ivHeart.setImageResource(R.drawable.ic_heart_empty)
        }
    }

    override fun getItemCount(): Int = productList.size
}