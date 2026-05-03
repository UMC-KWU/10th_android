package com.example.a10th_umc_week02

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.a10th_umc_week02.databinding.FragmentFollowingListPageBinding
import kotlinx.coroutines.launch

class FollowingListPage : Fragment() {
    private var _binding: FragmentFollowingListPageBinding? = null
    private val binding get() = _binding!!

    private val repository = AuthRepository(ApiClient.authService)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingListPageBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UserInfo()
        FollowingList()
    }

    private fun UserInfo() {
        viewLifecycleOwner.lifecycleScope.launch {
            val result = repository.Login()
            result.onSuccess { userData ->
                binding.tvMyNickname.text = "${userData.firstName} ${userData.lastName}"
                Glide.with(this@FollowingListPage)
                    .load(userData.avatar)
                    .into(binding.ivMyProfile)
                Log.d("tag", "화면 업데이트 완료")
            }.onFailure { e ->
                Log.e("tag", "실패: ${e.message}")
            }
        }
    }

    private fun FollowingList() {
        viewLifecycleOwner.lifecycleScope.launch {
            val result = repository.getFollowingList()
            result.onSuccess { list ->
                val adapter = FollowingAdapter(list)
                binding.rvFollowing.adapter = adapter
                binding.rvFollowing.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}