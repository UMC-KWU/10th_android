package com.neouul.umc10android.week05.presentation.fragment.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week05.databinding.ItemShopListBinding
import com.neouul.umc10android.week05.domain.model.Product

class ShopAdapter(
    private var shopList: MutableList<Product>,
    private val onVisitClicked: (Product) -> Unit,
    private val onWishClicked: (Product) -> Unit
) : RecyclerView.Adapter<ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding = ItemShopListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ShopViewHolder(binding, onVisitClicked, onWishClicked)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(shopList[position])
    }

    override fun getItemCount(): Int = shopList.size

    fun updateList(newList: List<Product>) {
        shopList = newList.toMutableList()
        notifyDataSetChanged()
    }
}