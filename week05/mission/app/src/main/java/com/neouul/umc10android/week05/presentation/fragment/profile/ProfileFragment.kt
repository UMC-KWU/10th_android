package com.neouul.umc10android.week05.presentation.fragment.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.neouul.umc10android.week05.R
import com.neouul.umc10android.week05.databinding.FragmentProfileBinding
import com.neouul.umc10android.week05.domain.model.User

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileAdapter: ProfileAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        setupRecyclerView()
        observeFollowing()

        binding.tvFollowing.text = "팔로잉 (${profileAdapter.getItemCount()})"
    }

    private fun setupRecyclerView() {
        profileAdapter = ProfileAdapter(
            mutableListOf(
                User(
                    id = 1,
                    email = "1@mail.com",
                    firstName = "one",
                    lastName = "one",
                    avatarUrl = ""
                ),
                User(
                    id = 2,
                    email = "2@mail.com",
                    firstName = "two",
                    lastName = "two",
                    avatarUrl = ""
                ),
            ),
            onVisitClicked = {
                // 팔로잉 중인 회원 페이지로 이동
            }
        )
        binding.followingRecyclerview.adapter = profileAdapter
        binding.followingRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun observeFollowing() {
//        profileAdapter.updateList(followingList)
    }
}