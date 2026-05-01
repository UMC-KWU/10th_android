package com.neouul.umc10android.week05.presentation.fragment.shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import com.neouul.umc10android.week05.R
import com.neouul.umc10android.week05.databinding.FragmentShopBinding

class ShopFragment : Fragment(R.layout.fragment_shop) {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShopBinding.bind(view)

        // 내부 NavHostFragment에서 NavController 찾기 (탭 전환용)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.tap.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val destinationId = when (tab?.position) {
                    0 -> R.id.shopTap0Fragment
                    1 -> R.id.shop1Fragment
                    2 -> R.id.shop2Fragment
                    else -> return
                }

                navController.navigate(destinationId) {
                    // 현재 선택된 탭의 인스턴스가 이미 위에 있으면 다시 생성하지 않음
                    launchSingleTop = true
                    // 탭 전환 시 이전 탭의 상태를 저장
                    restoreState = true
                    // 탭 전환 시 백스택이 쌓이지 않도록 시작 지점 위를 모두 비움
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}