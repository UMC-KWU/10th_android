package com.neouul.umc10android.week06.presentation.fragment.shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.google.android.material.tabs.TabLayout
import com.neouul.umc10android.week06.R
import com.neouul.umc10android.week06.databinding.FragmentShopBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment : Fragment(R.layout.fragment_shop) {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShopBinding.bind(view)

        // 내부 NavHostFragment에서 NavController 찾기 (탭 전환용)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.shop_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.tap.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val destinationId = when (tab?.position) {
                    0 -> R.id.shopTap0Fragment
                    1 -> R.id.shopTap1Fragment
                    2 -> R.id.shopTap2Fragment
                    else -> return
                }

                // 현재 목적지와 같으면 이동하지 않음 (중복 방지 및 오류 예방)
                if (navController.currentDestination?.id == destinationId) return

                navController.navigate(destinationId, null, navOptions {
                    // 현재 선택된 탭의 인스턴스가 이미 위에 있으면 다시 생성하지 않음
                    launchSingleTop = true
                    // 탭 전환 시 이전 탭의 상태를 저장
                    restoreState = true
                    // 탭 전환 시 백스택이 쌓이지 않도록 시작 지점 위를 모두 비움
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                })
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