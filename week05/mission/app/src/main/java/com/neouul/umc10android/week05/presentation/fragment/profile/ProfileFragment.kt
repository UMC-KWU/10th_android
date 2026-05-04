package com.neouul.umc10android.week05.presentation.fragment.profile

import android.os.Bundle
import com.neouul.umc10android.week05.BuildConfig
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.neouul.umc10android.week05.R
import com.neouul.umc10android.week05.core.MyApplication
import com.neouul.umc10android.week05.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileAdapter: ProfileAdapter
    private val userRepository by lazy {
        (requireActivity().application as MyApplication).container.userRepository
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        setupRecyclerView()
        loadUserData()
    }

    private fun setupRecyclerView() {
        profileAdapter = ProfileAdapter(
            mutableListOf(),
            onVisitClicked = {
                // 팔로잉 중인 회원 페이지로 이동
            }
        )
        binding.followingRecyclerview.adapter = profileAdapter
        binding.followingRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun loadUserData() {
        Log.d("ProfileFragment", "loadUserData() started")
        viewLifecycleOwner.lifecycleScope.launch {
            // 1. 사용자 목록 로드 (page = 1)
            userRepository.getUsers(page = 1, token = BuildConfig.AUTH_TOKEN).onSuccess { userList ->
                Log.d("ProfileFragment", "getUsers onSuccess: ${userList.size} items")
                profileAdapter.updateList(userList)
                binding.tvFollowing.text = "팔로잉 (${userList.size})"
            }.onFailure { e ->
                Log.e("ProfileFragment", "getUsers onFailure", e)
                // 에러 발생 시 토스트로 알림
                Toast.makeText(requireContext(), "목록 로드 실패: ${e.message}", Toast.LENGTH_SHORT).show()
            }

            // 2. 특정 사용자 조회 (id = 1)
            userRepository.getUserById(id = 1, token = BuildConfig.AUTH_TOKEN).onSuccess { user ->
                Log.d("ProfileFragment", "getUserById onSuccess: ${user.nickName}")
                binding.tvNickname.text = user.nickName
            }.onFailure { e ->
                Log.e("ProfileFragment", "getUserById onFailure", e)
                Toast.makeText(requireContext(), "개별 정보 로드 실패: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
