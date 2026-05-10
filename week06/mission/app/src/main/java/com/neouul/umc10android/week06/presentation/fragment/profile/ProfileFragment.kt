package com.neouul.umc10android.week06.presentation.fragment.profile

import android.os.Bundle
import com.neouul.umc10android.week06.BuildConfig
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.neouul.umc10android.week06.R
import com.neouul.umc10android.week06.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileAdapter: ProfileAdapter

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        setupRecyclerView()
        observeUiState()
        
        viewModel.loadUserData(BuildConfig.AUTH_TOKEN)
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

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect { state ->
                        profileAdapter.updateList(state.users)
                        binding.tvFollowing.text = "팔로잉 (${state.users.size})"
                        
                        state.currentUser?.let {
                            binding.tvNickname.text = it.nickName
                        }
                    }
                }
                launch {
                    viewModel.errorEvent.collect { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
