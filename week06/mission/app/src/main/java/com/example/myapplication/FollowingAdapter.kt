package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemFollowingBinding

class FollowingAdapter(private val followings: List<UserData>) :
    RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    inner class FollowingViewHolder(private val binding: ItemFollowingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserData) {
            binding.tvFollowingName.text = "${user.firstName} ${user.lastName}"
            Glide.with(binding.root.context)
                .load(user.avatar)
                .circleCrop()
                .into(binding.ivFollowing)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding = ItemFollowingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FollowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(followings[position])
    }

    override fun getItemCount() = followings.size
}