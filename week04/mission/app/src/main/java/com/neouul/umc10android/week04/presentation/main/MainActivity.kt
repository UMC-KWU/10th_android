package com.neouul.umc10android.week04.presentation.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.neouul.umc10android.week04.R
import com.neouul.umc10android.week04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent로 데이터 전달받기
        val title = intent.getStringExtra("TITLE") ?: "데이터 가져오기를 실패했습니다"
        val bundle = Bundle().apply {
            putString("title", title)
        }

        // 1. NavHostFragment를 찾아 NavController를 가져오기
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // 2. NavController에 graph를 설정하면서 bundle을 함께 전달
        if (savedInstanceState == null){
            navController.setGraph(R.navigation.nav_graph, bundle)
        }

        // 3. BottomNavigationView와 NavController를 연결하기
        binding.mainBnv.setupWithNavController(navController)

        // 하단 탭 클릭 시 상세 페이지가 열려있다면 닫고 이동하도록 설정
        binding.mainBnv.setOnItemSelectedListener { item ->
            if (navController.currentDestination?.id == R.id.detailFragment) {
                navController.popBackStack()
            }
            NavigationUI.onNavDestinationSelected(item, navController)
        }

        // 상세 페이지 진입 시 '구매하기' 탭 하이라이트 유지 로직
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.detailFragment) {
                binding.mainBnv.menu.findItem(R.id.shopFragment).isChecked = true
            }
        }
    }
}