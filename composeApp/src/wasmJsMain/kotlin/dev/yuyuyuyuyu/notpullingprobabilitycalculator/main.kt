package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.NotPullingProbabilityCalculatorApp
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        NotPullingProbabilityCalculatorApp()
    }
}