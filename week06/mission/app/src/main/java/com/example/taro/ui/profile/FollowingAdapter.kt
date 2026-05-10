package com.example.taro.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taro.data.model.ReqresUserDto
import com.example.taro.databinding.ItemFollowingBinding

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private val users = mutableListOf<ReqresUserDto>()

    inner class FollowingViewHolder(
        private val binding: ItemFollowingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: ReqresUserDto) {
            Glide.with(binding.ivFollowingAvatar.context)
                .load(user.avatar)
                .circleCrop()
                .into(binding.ivFollowingAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun submitList(newUsers: List<ReqresUserDto>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }
}