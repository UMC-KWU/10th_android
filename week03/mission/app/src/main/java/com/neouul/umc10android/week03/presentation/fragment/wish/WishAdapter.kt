package com.neouul.umc10android.week03.presentation.fragment.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week03.databinding.ItemWishListBinding
import com.neouul.umc10android.week03.domain.model.Product

class WishAdapter(
    private var wishList: MutableList<Product>,
    private val onVisitClicked: (Product) -> Unit
) : RecyclerView.Adapter<WishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishViewHolder {
        val binding = ItemWishListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        // 생성 시 클릭 리스너도 함께 넘겨줍니다.
        return WishViewHolder(binding, onVisitClicked)
    }

    override fun onBindViewHolder(holder: WishViewHolder, position: Int) {
        val nowWish = wishList[position]
        holder.bind(nowWish)
    }

    override fun getItemCount(): Int {
        return wishList.size
    }

}