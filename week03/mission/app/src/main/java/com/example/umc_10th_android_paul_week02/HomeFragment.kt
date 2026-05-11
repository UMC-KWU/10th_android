package com.example.umc_10th_android_paul_week02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_10th_android_paul_week02.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("M월 d일 E요일", Locale.KOREAN)
        dateFormat.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        binding.tvDate.text = dateFormat.format(currentTime)

        val homeProductList = arrayListOf(
            HomeProduct(R.drawable.shoe_jordan_36, "Air Jordan XXXVI", "US$185"),
            HomeProduct(R.drawable.shoe_jordan_01, "Air Jordan 1 Mid", "US$125"),
            HomeProduct(R.drawable.shoe_airforce_01, "Nike Air Force 1 '07", "US$115")
        )

        val homeAdapter = HomeProductAdapter(homeProductList)
        binding.rvHomeProduct.adapter = homeAdapter
        binding.rvHomeProduct.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}