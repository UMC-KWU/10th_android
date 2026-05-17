package com.example.umc_10th_android_paul_week02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val tvDate = view.findViewById<TextView>(R.id.tv_date)

        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("M월 d일 E요일", Locale.KOREAN)
        dateFormat.timeZone = java.util.TimeZone.getTimeZone("Asia/Seoul")

        val formattedDate = dateFormat.format(currentTime)

        tvDate.text = formattedDate

        return view
    }
}