package com.neouul.umc10android.week06.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.neouul.umc10android.week06.presentation.main.MainActivity
import com.neouul.umc10android.week06.R
import com.neouul.umc10android.week06.databinding.ActivitySplashBinding
import com.neouul.umc10android.week06.domain.repository.ProductRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var productRepository: ProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash) // 레이아웃 연결

        // 2초 뒤에 실행 (Coroutine 또는 Handler 사용)
        lifecycleScope.launch {
            productRepository.initializeDataIfNeeded()
            
            delay(2000) // 2초 대기

            val intent = Intent(this@SplashActivity, MainActivity::class.java).apply {
                // 데이터 전달
                putExtra("TITLE", "UMC week06")
            }
            startActivity(intent)

            finish()
        }
    }
}