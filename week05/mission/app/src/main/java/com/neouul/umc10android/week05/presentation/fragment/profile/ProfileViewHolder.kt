package com.neouul.umc10android.week05.presentation.fragment.profile

import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week05.databinding.ItemFollowingListBinding
import com.neouul.umc10android.week05.domain.model.User

class ProfileViewHolder(
    private val binding: ItemFollowingListBinding,
    private val onVisitClicked: (User) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
//        binding.ivProduct.setImageResource(home.image).text = home.name

        // 아이템 전체 클릭 리스너 설정
        binding.root.setOnClickListener {
            onVisitClicked(user)
        }

    }
}