package com.neouul.umc10android.week03.presentation.fragment.shop

import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week03.databinding.ItemShopListBinding
import com.neouul.umc10android.week03.domain.model.Product

class ShopViewHolder(
    private val binding: ItemShopListBinding,
    private val onVisitClicked: (Product) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(shop: Product) {
//        binding.tvName.text = shop.name
//        binding.shopTvStatus.text = shop.status
//        binding.shopVisitBtn.setOnClickListener {
//            onVisitClicked(shop)
//        }
    }
}