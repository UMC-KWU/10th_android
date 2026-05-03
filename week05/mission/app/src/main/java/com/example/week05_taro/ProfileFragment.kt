package com.example.week03_taro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.week03_taro.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var followingAdapter: FollowingAdapter

    private val reqresRepository = ReqresRepository()

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

        initFollowingRecyclerView()
        loadProfileUser()
        loadFollowingUsers()
    }

    private fun initFollowingRecyclerView() {
        followingAdapter = FollowingAdapter()

        binding.rvFollowing.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = followingAdapter
            setHasFixedSize(true)
        }
    }

    private fun loadProfileUser() {
        viewLifecycleOwner.lifecycleScope.launch {
            val result = reqresRepository.getUserById(USER_ID)

            result.onSuccess { user ->
                binding.tvProfileNickname.text = user.fullName

                Glide.with(binding.ivProfileAvatar)
                    .load(user.avatar)
                    .circleCrop()
                    .placeholder(R.drawable.bg_circle_gray)
                    .error(R.drawable.bg_circle_gray)
                    .into(binding.ivProfileAvatar)

                Log.d("ProfileFragment", "프로필 유저 조회 성공: ${user.fullName}")
            }.onFailure { error ->
                Log.d("ProfileFragment", "프로필 유저 조회 실패: ${error.message}")
                Toast.makeText(
                    requireContext(),
                    "프로필 정보를 불러오지 못했습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun loadFollowingUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            val result = reqresRepository.getUsers(page = 1)

            result.onSuccess { users ->
                followingAdapter.submitList(users)
                binding.tvFollowingTitle.text = "팔로잉 (${users.size})"

                Log.d("ProfileFragment", "팔로잉 리스트 조회 성공: ${users.size}명")
            }.onFailure { error ->
                Log.d("ProfileFragment", "팔로잉 리스트 조회 실패: ${error.message}")
                Toast.makeText(
                    requireContext(),
                    "팔로잉 목록을 불러오지 못했습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val USER_ID = 1
    }
}