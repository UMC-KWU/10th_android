package com.neouul.umc10android.week03.presentation.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.neouul.umc10android.week03.R
import com.neouul.umc10android.week03.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnOrder.setOnClickListener {
            // nav_graph.xml에 정의한 action ID를 사용해 이동합니다.
//            findNavController().navigate(R.id.action_cartFragment_to_shopFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}