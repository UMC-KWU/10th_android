package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton1.setOnClickListener {
            binding.id1.setTextColor(Color.BLUE)
        }
        binding.imageButton2.setOnClickListener {
            binding.id1.setTextColor(Color.RED)
        }
        binding.imageButton3.setOnClickListener {
            binding.id1.setTextColor(Color.GREEN)
        }
        binding.imageButton4.setOnClickListener {
            binding.id1.setTextColor(Color.YELLOW)
        }
        binding.imageButton5.setOnClickListener {
            binding.id1.setTextColor(Color.BLACK)
        }
    }
}