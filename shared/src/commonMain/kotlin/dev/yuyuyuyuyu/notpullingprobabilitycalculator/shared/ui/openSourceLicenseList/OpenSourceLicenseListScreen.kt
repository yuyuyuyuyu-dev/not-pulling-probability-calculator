package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.openSourceLicenseList

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen

interface OpenSourceLicenseListScreen : Screen {
    data object State : CircuitUiState
}
