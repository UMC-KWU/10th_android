package com.neouul.umc10android.week05.presentation.fragment.shop

import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week05.R
import com.neouul.umc10android.week05.databinding.ItemShopListBinding
import com.neouul.umc10android.week05.domain.model.Product

class ShopViewHolder(
    private val binding: ItemShopListBinding,
    private val onVisitClicked: (Product) -> Unit,
    private val onWishClicked: (Product) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(shop: Product) {
        binding.tvName.text = shop.name
        binding.tvDes.text = shop.description
        binding.tvColorNumber.text = "${shop.colorNumber} Colours"
        binding.tvPrice.text = shop.price
        
        // 위시리스트 상태에 따른 아이콘 변경
        binding.icHeart.setImageResource(
            if (shop.isWished) R.drawable.ic_heart_full
            else R.drawable.ic_heart_empty
        )

        // 아이템 전체 클릭 리스너 설정
        binding.root.setOnClickListener {
            onVisitClicked(shop)
        }

        // 하트 아이콘 클릭 리스너
        binding.icHeart.setOnClickListener {
            onWishClicked(shop)
        }
    }
}

//fun View.setVisibleOrGone(show: Boolean) {
//    this.visibility = if (show) View.VISIBLE else View.GONE
//}