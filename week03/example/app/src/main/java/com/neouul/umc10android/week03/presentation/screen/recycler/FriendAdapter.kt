package com.neouul.umc10android.week03.presentation.screen.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week03.databinding.ItemFriendBinding
import com.neouul.umc10android.week03.domain.model.FriendData

class FriendAdapter(
    private var friendList: MutableList<FriendData>,
    private val onVisitClicked: (FriendData) -> Unit
) : RecyclerView.Adapter<FriendViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ItemFriendBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        // 생성 시 클릭 리스너도 함께 넘겨줍니다.
        return FriendViewHolder(binding, onVisitClicked)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val nowFriend = friendList[position]
        holder.bind(nowFriend)
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

}