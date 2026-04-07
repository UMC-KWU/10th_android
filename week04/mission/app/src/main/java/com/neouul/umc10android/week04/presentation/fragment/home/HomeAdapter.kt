package com.neouul.umc10android.week04.presentation.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week04.databinding.ItemHomeListBinding
import com.neouul.umc10android.week04.domain.model.Product

class HomeAdapter(
    private var homeList: MutableList<Product>,
    private val onVisitClicked: (Product) -> Unit
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        // 생성 시 클릭 리스너도 함께 넘겨줍니다.
        return HomeViewHolder(binding, onVisitClicked)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val nowHome = homeList[position]
        holder.bind(nowHome)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

}