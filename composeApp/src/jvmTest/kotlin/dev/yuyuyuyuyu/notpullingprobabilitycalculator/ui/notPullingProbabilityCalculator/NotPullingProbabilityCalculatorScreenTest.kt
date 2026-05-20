package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.v2.runComposeUiTest
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.domain.useCases.CalculateNotPullingProbabilityUseCase
import java.util.Locale
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class NotPullingProbabilityCalculatorScreenTest {
    private lateinit var originalLocale: Locale

    @BeforeTest
    fun setUp() {
        originalLocale = Locale.getDefault()
        Locale.setDefault(Locale.ENGLISH)
    }

    @AfterTest
    fun tearDown() {
        Locale.setDefault(originalLocale)
    }

    @Test
    fun `displays calculated probabilities when 0_75 percent odds drawn 300 times`() =
        runComposeUiTest {
            // Arrange
            val viewModel = NotPullingProbabilityCalculatorViewModelImpl(
                CalculateNotPullingProbabilityUseCase(),
            )
            setContent {
                NotPullingProbabilityCalculatorScreen(viewModel = viewModel)
            }

            // Act
            onNodeWithTag(testTag = "oddsTextField").performTextInput(text = "0.75")
            onNodeWithTag(testTag = "trialsTextField").performTextInput(text = "300")

            waitForIdle()

            // Assert
            onNodeWithTag(testTag = "notPullingProbabilityValue").assertTextEquals("Approx. 10.45092258072379 %")
            onNodeWithTag(testTag = "pullingProbabilityValue").assertTextEquals("Approx. 89.54907741927622 %")
        }
}
