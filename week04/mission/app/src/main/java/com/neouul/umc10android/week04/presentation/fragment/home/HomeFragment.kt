package com.neouul.umc10android.week04.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.neouul.umc10android.week04.NavGraphDirections
import com.neouul.umc10android.week04.R
import com.neouul.umc10android.week04.core.MyApplication
import com.neouul.umc10android.week04.databinding.FragmentHomeBinding
import com.neouul.umc10android.week04.domain.model.Product
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {
    // 마지막으로 시스템 뒤로가기를 누른 시간을 저장할 변수
    private var backPressedTime: Long = 0

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val productRepository by lazy {
        (requireActivity().application as MyApplication).container.productRepository
    }

    private lateinit var homeAdapter: HomeAdapter

    // Safe Args 대리자(Delegate) 선언
    private val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        // 전달받은 TITLE 데이터 가져오기
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
            productRepository.getHomeProducts().collect { products ->
                homeAdapter.updateList(products)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}