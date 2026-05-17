package com.example.umc_10th_android_paul

import android.os.Bundle
import android.graphics.Color
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 각 세트의 상태를 저장할 변수 (false: 검정, true: 색상변화)
        var isGreatSelected = false
        var isHappySelected = false
        var isNormalSelected = false
        var isAnxietySelected = false
        var isAngrySelected = false

        // 1. 첫 번째 세트 (Great)
        val ivGreat = findViewById<android.widget.ImageView>(R.id.great)
        val tvGreat = findViewById<android.widget.TextView>(R.id.great_text)
        ivGreat.setOnClickListener {
            if (isGreatSelected) {
                tvGreat.setTextColor(Color.BLACK)
            } else {
                tvGreat.setTextColor(Color.YELLOW)
            }
            isGreatSelected = !isGreatSelected // 상태 반전 (true -> false, false -> true)
        }

        // 2. 두 번째 세트 (Happy)
        val ivHappy = findViewById<android.widget.ImageView>(R.id.happy)
        val tvHappy = findViewById<android.widget.TextView>(R.id.happy_text)
        ivHappy.setOnClickListener {
            if (isHappySelected) {
                tvHappy.setTextColor(Color.BLACK)
            } else {
                tvHappy.setTextColor(Color.BLUE)
            }
            isHappySelected = !isHappySelected
        }

        // 3. 세 번째 세트 (Normal)
        val ivNormal = findViewById<android.widget.ImageView>(R.id.normal)
        val tvNormal = findViewById<android.widget.TextView>(R.id.normal_text)
        ivNormal.setOnClickListener {
            if (isNormalSelected) {
                tvNormal.setTextColor(Color.BLACK)
            } else {
                tvNormal.setTextColor(Color.MAGENTA)
            }
            isNormalSelected = !isNormalSelected
        }

        // 4. 네 번째 세트 (Anxiety)
        val ivAnxiety = findViewById<android.widget.ImageView>(R.id.anxiety)
        val tvAnxiety = findViewById<android.widget.TextView>(R.id.anxiety_text)
        ivAnxiety.setOnClickListener {
            if (isAnxietySelected) {
                tvAnxiety.setTextColor(Color.BLACK)
            } else {
                tvAnxiety.setTextColor(Color.GREEN)
            }
            isAnxietySelected = !isAnxietySelected
        }

        // 5. 다섯 번째 세트 (Angry)
        val ivAngry = findViewById<android.widget.ImageView>(R.id.angry)
        val tvAngry = findViewById<android.widget.TextView>(R.id.angry_text)
        ivAngry.setOnClickListener {
            if (isAngrySelected) {
                tvAngry.setTextColor(Color.BLACK)
            } else {
                tvAngry.setTextColor(Color.RED)
            }
            isAngrySelected = !isAngrySelected
        }
    }
}