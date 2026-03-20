package com.example.week01_taro

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var ivBack: ImageView
    private lateinit var ivHappyStamp: ImageView
    private lateinit var ivExcitedStamp: ImageView
    private lateinit var ivNormalStamp: ImageView
    private lateinit var ivAnxiousStamp: ImageView
    private lateinit var ivAngryStamp: ImageView

    private lateinit var tvHappyLabel: TextView
    private lateinit var tvExcitedLabel: TextView
    private lateinit var tvNormalLabel: TextView
    private lateinit var tvAnxiousLabel: TextView
    private lateinit var tvAngryLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivBack = findViewById(R.id.ivBack)
        ivHappyStamp = findViewById(R.id.ivHappyStamp)
        ivExcitedStamp = findViewById(R.id.ivExcitedStamp)
        ivNormalStamp = findViewById(R.id.ivNormalStamp)
        ivAnxiousStamp = findViewById(R.id.ivAnxiousStamp)
        ivAngryStamp = findViewById(R.id.ivAngryStamp)

        tvHappyLabel = findViewById(R.id.tvHappyLabel)
        tvExcitedLabel = findViewById(R.id.tvExcitedLabel)
        tvNormalLabel = findViewById(R.id.tvNormalLabel)
        tvAnxiousLabel = findViewById(R.id.tvAnxiousLabel)
        tvAngryLabel = findViewById(R.id.tvAngryLabel)

        ivBack.setOnClickListener {
            finish()
        }

        ivHappyStamp.setOnClickListener {
            selectEmotion(tvHappyLabel, R.color.emotion_happy)
        }

        ivExcitedStamp.setOnClickListener {
            selectEmotion(tvExcitedLabel, R.color.emotion_excited)
        }

        ivNormalStamp.setOnClickListener {
            selectEmotion(tvNormalLabel, R.color.emotion_normal)
        }

        ivAnxiousStamp.setOnClickListener {
            selectEmotion(tvAnxiousLabel, R.color.emotion_anxious)
        }

        ivAngryStamp.setOnClickListener {
            selectEmotion(tvAngryLabel, R.color.emotion_angry)
        }
    }

    private fun selectEmotion(selectedTextView: TextView, colorResId: Int) {
        resetEmotionTextColors()
        selectedTextView.setTextColor(ContextCompat.getColor(this, colorResId))
    }

    private fun resetEmotionTextColors() {
        val defaultColor = ContextCompat.getColor(this, R.color.emotion_default)

        tvHappyLabel.setTextColor(defaultColor)
        tvExcitedLabel.setTextColor(defaultColor)
        tvNormalLabel.setTextColor(defaultColor)
        tvAnxiousLabel.setTextColor(defaultColor)
        tvAngryLabel.setTextColor(defaultColor)
    }
}