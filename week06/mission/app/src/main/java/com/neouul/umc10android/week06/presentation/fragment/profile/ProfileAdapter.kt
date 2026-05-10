package com.neouul.umc10android.week06.presentation.fragment.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.week06.databinding.ItemFollowingListBinding
import com.neouul.umc10android.week06.domain.model.User

class ProfileAdapter(
    private var ProfileList: MutableList<User>,
    private val onVisitClicked: (User) -> Unit
) : RecyclerView.Adapter<ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemFollowingListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ProfileViewHolder(binding, onVisitClicked)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(ProfileList[position])
    }

    override fun getItemCount(): Int = ProfileList.size

    fun updateList(newList: List<User>) {
        ProfileList = newList.toMutableList()
        notifyDataSetChanged()
    }
}