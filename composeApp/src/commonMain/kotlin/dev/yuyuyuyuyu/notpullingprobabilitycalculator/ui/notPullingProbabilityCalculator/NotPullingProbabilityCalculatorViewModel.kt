package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator

import kotlinx.coroutines.flow.StateFlow

interface NotPullingProbabilityCalculatorViewModel {
    val uiState: StateFlow<NotPullingProbabilityCalculatorUiState>

    fun onOddsChange(newValue: String)
    fun onNumberOfTrialsChange(newValue: String)
}