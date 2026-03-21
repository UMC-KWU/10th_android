package com.example.a10th_umc_week01

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a10th_umc_week01.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.good.setOnClickListener {
            binding.tgood.setTextColor(Color.YELLOW)
        }
        binding.happy.setOnClickListener {
            binding.thappy.setTextColor(Color.parseColor("#87CEEB"))
        }
        binding.cold.setOnClickListener {
            binding.tcold.setTextColor(Color.BLUE)
        }
        binding.bored.setOnClickListener {
            binding.tbored.setTextColor(Color.GREEN)
        }
        binding.angry.setOnClickListener {
            binding.tangry.setTextColor(Color.RED)
        }
    }
}
