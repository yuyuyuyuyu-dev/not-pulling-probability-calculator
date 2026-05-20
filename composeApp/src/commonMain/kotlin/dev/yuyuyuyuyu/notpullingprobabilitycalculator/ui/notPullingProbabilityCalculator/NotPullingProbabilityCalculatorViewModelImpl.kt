package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator

import androidx.lifecycle.ViewModel
import com.github.michaelbull.result.onErr
import com.github.michaelbull.result.onOk
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.domain.useCases.CalculateNotPullingProbabilityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject

@Inject
class NotPullingProbabilityCalculatorViewModelImpl(
    private val calculateNotPullingProbabilityUseCase: CalculateNotPullingProbabilityUseCase,
) : ViewModel(),
    NotPullingProbabilityCalculatorViewModel {
    private val _uiState =
        MutableStateFlow(
            NotPullingProbabilityCalculatorUiState(
                odds = "",
                numberOfTrials = "",
                pullingProbability = "",
                notPullingProbability = "",
            ),
        )
    override val uiState: StateFlow<NotPullingProbabilityCalculatorUiState> = _uiState.asStateFlow()

    override fun onOddsChange(newValue: String) {
        _uiState.update { it.copy(odds = newValue) }
        calculate()
    }

    override fun onNumberOfTrialsChange(newValue: String) {
        _uiState.update { it.copy(numberOfTrials = newValue) }
        calculate()
    }

    private fun calculate() {
        val currentOdds = _uiState.value.odds.toDoubleOrNull()
        val currentTrials = _uiState.value.numberOfTrials.toIntOrNull()

        if (currentOdds != null && currentTrials != null) {
            calculateNotPullingProbabilityUseCase(currentOdds, currentTrials)
                .onOk { probability ->
                    val notPulling = (probability.notPulling * 100).toString()
                    val pulling = ((1.0 - probability.notPulling) * 100).toString()
                    _uiState.update {
                        it.copy(
                            notPullingProbability = notPulling,
                            pullingProbability = pulling,
                        )
                    }
                }.onErr {
                    _uiState.update {
                        it.copy(
                            notPullingProbability = "",
                            pullingProbability = "",
                        )
                    }
                }
        } else {
            _uiState.update {
                it.copy(
                    notPullingProbability = "",
                    pullingProbability = "",
                )
            }
        }
    }
}
