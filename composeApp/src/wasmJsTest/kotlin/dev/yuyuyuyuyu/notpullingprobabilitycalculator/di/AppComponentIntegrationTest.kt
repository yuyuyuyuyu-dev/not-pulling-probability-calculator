package dev.yuyuyuyuyu.notpullingprobabilitycalculator.di

import kotlin.test.Test
import kotlin.test.assertEquals

class AppComponentIntegrationTest {
    @Test
    fun `app component wires a view model backed by the real use case`() {
        // Arrange
        val appComponent = createAppComponent()
        val viewModel = appComponent.notPullingProbabilityCalculatorViewModel

        // Act
        viewModel.onOddsChange("0.75")
        viewModel.onNumberOfTrialsChange("300")

        // Assert
        val state = viewModel.uiState.value
        assertEquals("10.45092258072379", state.notPullingProbability)
        assertEquals("89.54907741927622", state.pullingProbability)
    }
}
