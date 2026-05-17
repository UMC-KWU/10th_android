package com.neouul.umc10android.week07.presentation.screen.wish

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.neouul.umc10android.week07.ui.AppColors

@Composable
fun WishScreen(
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(AppColors.white)
    ) { }
    Text(
        text = "위시리스트"
    )
}