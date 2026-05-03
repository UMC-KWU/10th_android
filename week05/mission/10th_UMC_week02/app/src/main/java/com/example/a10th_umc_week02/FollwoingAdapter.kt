package com.example.a10th_umc_week02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FollowingAdapter(private val userList: List<UserData>) :
    RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar: ImageView = view.findViewById(R.id.ivItemAvatar)
        val tvName: TextView = view.findViewById(R.id.tvItemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_following, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        holder.tvName.text = "${user.firstName} ${user.lastName}"

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .into(holder.ivAvatar)
    }

    override fun getItemCount(): Int = userList.size
}