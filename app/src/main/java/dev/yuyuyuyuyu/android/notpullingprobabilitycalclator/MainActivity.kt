package dev.yuyuyuyuyu.android.notpullingprobabilitycalclator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.NotPullingProbabilityCalculatorApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotPullingProbabilityCalculatorApp()
        }
    }
}
