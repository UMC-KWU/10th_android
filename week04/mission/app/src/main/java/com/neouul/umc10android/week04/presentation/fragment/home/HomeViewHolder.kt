package com.neouul.umc10android.week04.presentation.fragment.home

import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week04.databinding.ItemHomeListBinding
import com.neouul.umc10android.week04.domain.model.Product

class HomeViewHolder(
    private val binding: ItemHomeListBinding,
    private val onVisitClicked: (Product) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(home: Product) {
        binding.tvName.text = home.name
        binding.tvPrice.text = home.price
        
        // 아이템 전체 클릭 리스너 설정
        binding.root.setOnClickListener {
            onVisitClicked(home)
        }

    }
}