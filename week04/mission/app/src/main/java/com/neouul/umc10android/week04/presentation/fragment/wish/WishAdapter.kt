package com.neouul.umc10android.week04.presentation.fragment.wish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week04.databinding.ItemWishListBinding
import com.neouul.umc10android.week04.domain.model.Product

class WishAdapter(
    private var wishList: MutableList<Product>,
    private val onVisitClicked: (Product) -> Unit
) : RecyclerView.Adapter<WishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishViewHolder {
        val binding = ItemWishListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return WishViewHolder(binding, onVisitClicked)
    }

    override fun onBindViewHolder(holder: WishViewHolder, position: Int) {
        holder.bind(wishList[position])
    }

    override fun getItemCount(): Int = wishList.size

    fun updateList(newList: List<Product>) {
        wishList = newList.toMutableList()
        notifyDataSetChanged()
    }
}