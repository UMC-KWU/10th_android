package com.neouul.umc10android.week03.presentation.fragment.shop

import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week03.databinding.ItemShopListBinding
import com.neouul.umc10android.week03.domain.model.Product

class ShopViewHolder(
    private val binding: ItemShopListBinding,
    private val onVisitClicked: (Product) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(shop: Product) {
        binding.tvName.text = shop.name
        binding.tvDes.text = shop.description
        binding.tvColorNumber.text = "${shop.colorNumber} Colours"
        binding.tvPrice.text = shop.price
        
        // 아이템 전체 클릭 리스너 설정
        binding.root.setOnClickListener {
            onVisitClicked(shop)
        }

        // 하트 아이콘 클릭 리스너
        binding.icHeart.setOnClickListener {

        }
    }
}

//fun View.setVisibleOrGone(show: Boolean) {
//    this.visibility = if (show) View.VISIBLE else View.GONE
//}