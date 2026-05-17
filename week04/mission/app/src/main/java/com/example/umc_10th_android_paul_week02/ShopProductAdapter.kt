package com.example.umc_10th_android_paul_week02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_10th_android_paul_week02.databinding.ItemShopProductBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopProductAdapter(private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<ShopProductAdapter.ViewHolder>() {
    lateinit var dataStoreManager: DataStoreManager

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

        updateHeartIcon(holder, product.isLiked)

        // 하트 클릭 시 상태 변경 및 저장 로직
        holder.binding.ivHeart.setOnClickListener {
            product.isLiked = !product.isLiked // 상태 반전
            updateHeartIcon(holder, product.isLiked)

            CoroutineScope(Dispatchers.IO).launch {
                dataStoreManager.saveWishlist(productList)
            }
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
}