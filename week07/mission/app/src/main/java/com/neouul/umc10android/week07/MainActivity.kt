package com.neouul.umc10android.week07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.neouul.umc10android.week07.core.routing.NavigationRoot
import com.neouul.umc10android.week07.ui.theme.UMC10thWeek07ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UMC10thWeek07ComposeTheme {
                NavigationRoot()
            }
        }
    }
}