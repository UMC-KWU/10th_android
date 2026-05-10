package com.example.a10th_umc_week02.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.a10th_umc_week02.databinding.FragmentFollowingListPageBinding
import com.example.a10th_umc_week02.ui.main.adapter.FollowingAdapter
import com.example.a10th_umc_week02.ui.main.viewmodel.FollowingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FollowingListPage : Fragment() {
    private var _binding: FragmentFollowingListPageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FollowingViewModel by viewModels()

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

        initObservers()
        viewModel.loadData()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myInfo.collect { userData ->
                    userData?.let {
                        binding.tvMyNickname.text = "${it.firstName} ${it.lastName}"
                        Glide.with(this@FollowingListPage).load(it.avatar).into(binding.ivMyProfile)
                    }
                }
            }
        }

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.followingList.collect { list ->
                        binding.rvFollowing.adapter = FollowingAdapter(list)
                        binding.rvFollowing.layoutManager = LinearLayoutManager(requireContext())
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}