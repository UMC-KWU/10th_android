package com.neouul.umc10android.week05.presentation.fragment.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.neouul.umc10android.week05.NavGraphDirections
import com.neouul.umc10android.week05.R
import com.neouul.umc10android.week05.core.MyApplication
import com.neouul.umc10android.week05.core.hideLoading
import com.neouul.umc10android.week05.core.showLoading
import com.neouul.umc10android.week05.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var backPressedTime: Long = 0
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val productRepository by lazy {
        (requireActivity().application as MyApplication).container.productRepository
    }

    private lateinit var homeAdapter: HomeAdapter
    private val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val title = args.title
        binding.tvTitle.text = title

        setupBackPressedCallback()
        setupRecyclerView()
        observeProducts()
    }

    private fun setupBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - backPressedTime < 2000) {
                    requireActivity().finish()
                } else {
                    backPressedTime = System.currentTimeMillis()
                    Toast.makeText(requireContext(), "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter(
            mutableListOf(),
            onVisitClicked = { product ->
                val action = NavGraphDirections.actionGlobalToDetailFragment(product.id)
                findNavController().navigate(action)
            }
        )
        binding.homeRecyclerview.adapter = homeAdapter
        binding.homeRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun observeProducts() {
        viewLifecycleOwner.lifecycleScope.launch {
            showLoading() // 로딩 시작
            kotlinx.coroutines.delay(1000) // 1초 가상 지연
            productRepository.getHomeProducts().collect { products ->
                homeAdapter.updateList(products)
                hideLoading() // 로딩 종료
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}