package com.neouul.umc10android.week03.presentation.fragment.wish

import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week03.databinding.ItemWishListBinding
import com.neouul.umc10android.week03.domain.model.Product

class WishViewHolder(
    private val binding: ItemWishListBinding,
    private val onVisitClicked: (Product) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(wish: Product) {
        binding.tvName.text = wish.name
        binding.tvDes.text = wish.description
        binding.tvColorNumber.text = "${wish.colorNumber} Colours"
        binding.tvPrice.text = wish.price
        
        // 아이템 전체 클릭 리스너 설정
        binding.root.setOnClickListener {
            onVisitClicked(wish)
        }

    }
}