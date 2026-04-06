package com.neouul.umc10android.week03.presentation.fragment.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week03.databinding.ItemShopListBinding
import com.neouul.umc10android.week03.domain.model.Product

class ShopAdapter(
    private var shopList: MutableList<Product>,
    private val onVisitClicked: (Product) -> Unit
) : RecyclerView.Adapter<ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding = ItemShopListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        // 생성 시 클릭 리스너도 함께 넘겨줍니다.
        return ShopViewHolder(binding, onVisitClicked)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val nowShop = shopList[position]
        holder.bind(nowShop)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

}