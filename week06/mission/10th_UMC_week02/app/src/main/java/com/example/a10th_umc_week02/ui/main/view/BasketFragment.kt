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
import com.example.a10th_umc_week02.R
import com.example.a10th_umc_week02.data.model.BuyData
import com.example.a10th_umc_week02.databinding.FragmentBasketBinding
import com.example.a10th_umc_week02.ui.main.adapter.BuyAdapter
import com.example.a10th_umc_week02.ui.main.viewmodel.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasketFragment : Fragment() {

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BasketViewModel by viewModels()
    private lateinit var basketAdapter: BuyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        basketAdapter = BuyAdapter(productList = emptyList())
        binding.basketRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.basketRecyclerview.adapter = basketAdapter
    }

    private fun setupListeners() {
        binding.buybutton.setOnClickListener {
            findNavController().navigate(R.id.action_basketFragment_to_buyFragment)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.basketList.collect { newList: List<BuyData> ->
                    basketAdapter.updateData(newList)
                    if (newList.isEmpty()) {
                        binding.basketRecyclerview.visibility = View.GONE
                        binding.bagcircle.visibility = View.VISIBLE
                        binding.date.visibility = View.VISIBLE
                    } else {
                        binding.basketRecyclerview.visibility = View.VISIBLE
                        binding.bagcircle.visibility = View.GONE
                        binding.date.visibility = View.GONE
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