package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.di.createAppComponent
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.main.MainScreen

@Composable
@Preview
fun App() {
    val appComponent = remember { createAppComponent() }

    MyMaterialTheme {
        MainScreen(
            notPullingProbabilityCalculatorViewModel = appComponent.notPullingProbabilityCalculatorViewModel,
        )
    }
}
