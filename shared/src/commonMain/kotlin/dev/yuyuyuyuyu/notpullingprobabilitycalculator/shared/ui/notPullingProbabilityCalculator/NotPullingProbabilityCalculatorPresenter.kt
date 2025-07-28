package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.slack.circuit.runtime.presenter.Presenter
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.useCases.CalculateNotPullingProbabilityUseCase

class NotPullingProbabilityCalculatorPresenter(
    private val calculateNotPullingProbabilityUseCase: CalculateNotPullingProbabilityUseCase,
) : Presenter<NotPullingProbabilityCalculatorScreen.State> {
    @Composable
    override fun present(): NotPullingProbabilityCalculatorScreen.State {
        var pullingProbability by rememberSaveable { mutableStateOf("") }
        var notPullingProbability by rememberSaveable { mutableStateOf("") }

        return NotPullingProbabilityCalculatorScreen.State(pullingProbability, notPullingProbability) { event ->
            when (event) {
                is NotPullingProbabilityCalculatorScreen.Event.ChangeInputValue -> {
                    val probability = calculateNotPullingProbabilityUseCase(
                        odds = event.odds,
                        numberOfTrials = event.numberOfTrials
                    )

                    pullingProbability = (probability.pulling * 100).toString()
                    notPullingProbability = (probability.notPulling * 100).toString()
                }
            }
        }
    }
}
