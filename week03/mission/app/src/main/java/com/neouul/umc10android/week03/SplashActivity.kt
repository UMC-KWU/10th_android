package com.neouul.umc10android.week03

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.neouul.umc10android.week03.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash) // 레이아웃 연결

        // 2초 뒤에 실행 (Coroutine 또는 Handler 사용)
        lifecycleScope.launch {
            delay(2000) // 2초 대기

            val intent = Intent(this@SplashActivity, MainActivity::class.java).apply {
                // 데이터 전달
                putExtra("TITLE", "UMC week2")
            }
            startActivity(intent)

            // SplashActivity를 종료해야 메인에서 '뒤로가기'를 눌러도 다시 스플래시가 안 뜸
            finish()
        }
    }
}