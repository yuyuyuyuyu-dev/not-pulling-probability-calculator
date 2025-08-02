package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.NotPullingProbabilityCalculatorApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "notpullingprobabilitycalculator",
    ) {
        NotPullingProbabilityCalculatorApp()
    }
}