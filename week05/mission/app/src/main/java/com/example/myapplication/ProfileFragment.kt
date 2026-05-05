package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchUserProfile()
        fetchFollowingList()
    }

    private fun fetchUserProfile() {
        RetrofitClient.api.getUser(1).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val user = response.body()?.data ?: return
                    binding.tvNickname.text = "${user.firstName} ${user.lastName}"
                    Glide.with(requireContext())
                        .load(user.avatar)
                        .circleCrop()
                        .into(binding.ivProfile)
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // 에러 처리
            }
        })
    }

    private fun fetchFollowingList() {
        RetrofitClient.api.getUserList(1).enqueue(object : Callback<UserListResponse> {
            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {
                if (response.isSuccessful) {
                    val users = response.body()?.data ?: return
                    binding.tvFollowingCount.text = "팔로잉 (${users.size})"
                    binding.rvFollowing.apply {
                        layoutManager = LinearLayoutManager(
                            context, LinearLayoutManager.HORIZONTAL, false
                        )
                        adapter = FollowingAdapter(users)
                    }
                }
            }
            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                // 에러 처리
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}