package com.example.umc_10th_android_paul_week02

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
class CartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        val btnOrder = view.findViewById<Button>(R.id.btn_order)

        btnOrder?.setOnClickListener {
            (activity as? MainActivity)?.changeTab(R.id.nav_shop)
        }
        return view
    }
}