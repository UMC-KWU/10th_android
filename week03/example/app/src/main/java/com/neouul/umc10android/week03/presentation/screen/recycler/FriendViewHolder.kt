package com.neouul.umc10android.week03.presentation.screen.recycler

import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week03.databinding.ItemFriendBinding
import com.neouul.umc10android.week03.domain.model.FriendData

class FriendViewHolder(
    private val binding: ItemFriendBinding,
    private val onVisitClicked: (FriendData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(friend: FriendData) {
        binding.friendTvName.text = friend.name
        binding.friendTvStatus.text = friend.status
        binding.friendVisitBtn.setOnClickListener {
            onVisitClicked(friend)
        }
    }
}