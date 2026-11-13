package com.example.pr_7_pr_24102_vopilovavs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pr_7_pr_24102_vopilovavs.ui.theme.Pr7_PR24102_VopilovaVSTheme
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pr7_PR24102_VopilovaVSTheme {
                SplashScreen(onFinished = {
                    // сюда потом: переход на следующий экран
                })
            }
        }
    }
}