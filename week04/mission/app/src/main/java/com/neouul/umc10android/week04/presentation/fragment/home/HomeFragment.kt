package com.neouul.umc10android.week04.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.neouul.umc10android.week04.NavGraphDirections
import com.neouul.umc10android.week04.R
import com.neouul.umc10android.week04.databinding.FragmentHomeBinding
import com.neouul.umc10android.week04.domain.model.Product

class HomeFragment : Fragment(R.layout.fragment_home) {
    // 마지막으로 시스템 뒤로가기를 누른 시간을 저장할 변수
    private var backPressedTime: Long = 0

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Safe Args 대리자(Delegate) 선언
    private val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 전달받은 TITLE 데이터 가져오기
        val title = args.title

        // TextView에 값 설정하기
        binding.tvTitle.text = title

        // 콜백 등록
        // 시스템 뒤로가기 2번 연속으로 터치하면 Activity 종료시키기
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - backPressedTime < 2000) {
                    // 2초 이내에 다시 누르면 액티비티 종료
                    requireActivity().finish()
                } else {
                    // 처음 눌렀을 때 메시지 표시
                    backPressedTime = System.currentTimeMillis()
                    Toast.makeText(requireContext(), "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })


        // 1. 표시할 데이터 목록 생성
        val productList = mutableListOf<Product>(
            Product(
                id = 3L,
                name = "Nike Air Force 1 '07",
                description = "Men's Shoes",
                detailDescription = "The radiance lives on in the Nike Air Force 1 '07.",
                category = "Lifestyle",
                colorNumber = 2,
                price = "US\$115",
                img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/b7d9211c-26e7-431a-ac24-b0540fb3c00f/air-force-1-07-mens-shoes-jps0P8.png",
                isWished = true
            ),
            Product(
                id = 5L,
                name = "Nike Pegasus 40",
                description = "Men's Road Running Shoes",
                detailDescription = "A springy ride for every run.",
                category = "Running",
                colorNumber = 7,
                price = "US\$130",
                img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/e66271e1-e170-4cc8-9441-26c92d50e80e/pegasus-40-mens-road-running-shoes-MCZ9Hz.png"
            ),
            Product(
                id = 6L,
                name = "Nike Blazer Mid '77 Vintage",
                description = "Men's Shoes",
                detailDescription = "Vintage style, modern comfort.",
                category = "Lifestyle",
                colorNumber = 2,
                price = "US\$105",
                img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/60451e06-5f73-45f8-842e-9d261e47f526/blazer-mid-77-vintage-mens-shoes-8N6R5v.png"
            )
        )

        // 2. 어댑터 인스턴스 생성 및 RecyclerView에 연결
        val adapter = HomeAdapter(
            productList,
            onVisitClicked = { product ->
                val action = NavGraphDirections.actionGlobalToDetailFragment(product.id)
                findNavController().navigate(action)
            })

        // 3. 어댑터 연결 및 LayoutManager 설정
        binding.homeRecyclerview.adapter = adapter
        binding.homeRecyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}