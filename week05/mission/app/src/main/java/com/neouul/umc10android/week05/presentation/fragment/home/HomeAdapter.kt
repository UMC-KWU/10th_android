package com.neouul.umc10android.week05.presentation.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week05.databinding.ItemHomeListBinding
import com.neouul.umc10android.week05.domain.model.Product

class HomeAdapter(
    private var homeList: MutableList<Product>,
    private val onVisitClicked: (Product) -> Unit
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return HomeViewHolder(binding, onVisitClicked)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(homeList[position])
    }

    override fun getItemCount(): Int = homeList.size

    fun updateList(newList: List<Product>) {
        homeList = newList.toMutableList()
        notifyDataSetChanged()
    }
}