package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator

import dev.yuyuyuyuyu.notpullingprobabilitycalculator.domain.useCases.CalculateNotPullingProbabilityUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NotPullingProbabilityCalculatorViewModelTest {
    private lateinit var viewModel: NotPullingProbabilityCalculatorViewModel

    @BeforeTest
    fun setUp() {
        val useCase = CalculateNotPullingProbabilityUseCase()
        viewModel = NotPullingProbabilityCalculatorViewModelImpl(useCase)
    }

    @Test
    fun `calculates probabilities when 0_75 percent odds drawn 300 times`() {
        // Arrange
        // (Initial state already set up in @BeforeTest)

        // Act
        viewModel.onOddsChange("0.75")
        viewModel.onNumberOfTrialsChange("300")

        // Assert
        val state = viewModel.uiState.value
        assertEquals("10.45092258072379", state.notPullingProbability)
        assertEquals("89.54907741927622", state.pullingProbability)
    }
}
