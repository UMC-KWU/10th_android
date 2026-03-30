package com.neouul.umc10android.week02

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.neouul.umc10android.week02.databinding.ActivityMainBinding

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
        navController.setGraph(R.navigation.nav_graph, bundle)

        // 3. BottomNavigationView와 NavController를 연결하기
        binding.mainBnv.setupWithNavController(navController)
    }
}