package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator

import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen

interface NotPullingProbabilityCalculatorScreen : Screen {
    data class State(
        val pullingProbability: String,
        val notPullingProbability: String,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed class Event : CircuitUiEvent {
        data class ChangeInputValue(val odds: Double, val numberOfTrials: Int) : Event()
    }
}
