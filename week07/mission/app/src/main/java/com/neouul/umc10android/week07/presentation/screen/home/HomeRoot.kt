package com.neouul.umc10android.week07.presentation.screen.home

import android.app.Activity
import android.widget.Toast
import android.window.SplashScreen
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.neouul.umc10android.week07.core.routing.Route
import kotlinx.coroutines.delay

@Composable
fun HomeRoot(
    title: String
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var backPressedTime by remember { mutableLongStateOf(0L) }

    BackHandler {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            (context as? Activity)?.finish()
        } else {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(context, "'뒤로' 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }

    HomeScreen(
        title = title,
        scrollState = scrollState
    )
}