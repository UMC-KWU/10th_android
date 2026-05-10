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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a10th_umc_week02.data.model.HomeData
import com.example.a10th_umc_week02.R
import com.example.a10th_umc_week02.databinding.FragmentHomeBinding
import com.example.a10th_umc_week02.ui.main.Click
import com.example.a10th_umc_week02.ui.main.adapter.HomeAdapter
import com.example.a10th_umc_week02.ui.main.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), Click{

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeAdapter(mutableListOf(), this)
        binding.HomeRecyclerview.adapter = homeAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeList.collect { newList: List<HomeData> ->
                    homeAdapter.updateData(newList)
                }
            }
        }
    }
    override fun onClick(product: HomeData) {
        findNavController().navigate(R.id.action_homeFragment_to_buyFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}