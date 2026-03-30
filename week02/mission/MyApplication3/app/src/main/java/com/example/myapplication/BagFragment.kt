package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentBagBinding

class BagFragment : Fragment() {

    private var _binding: FragmentBagBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBagBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOrder.setOnClickListener {
            if (isBagEmpty()) {
                navigateToShop()
            }
        }
    }

    private fun isBagEmpty(): Boolean {
        //미래를 위해 설계
        return true
    }

    private fun navigateToShop() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragmentContainer, BuyFragment())
            .commit()

        requireActivity().findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(
            R.id.main_bnv
        ).selectedItemId = R.id.buy
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}