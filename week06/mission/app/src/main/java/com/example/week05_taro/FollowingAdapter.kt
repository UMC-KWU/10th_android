package com.example.week03_taro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week03_taro.databinding.ItemFollowingBinding

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private val userList = mutableListOf<ReqresUserDto>()

    inner class FollowingViewHolder(
        private val binding: ItemFollowingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: ReqresUserDto) {
            Glide.with(binding.ivFollowingAvatar)
                .load(user.avatar)
                .centerCrop()
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
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    fun submitList(newList: List<ReqresUserDto>) {
        userList.clear()
        userList.addAll(newList)
        notifyDataSetChanged()
    }
}