package dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.notPullingProbabilityCalculator

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.presenter.Presenter

class NotPullingProbabilityCalculatorPresenter :
    Presenter<NotPullingProbabilityCalculatorScreen.State> {
    @Composable
    override fun present(): NotPullingProbabilityCalculatorScreen.State {
        return NotPullingProbabilityCalculatorScreen.State
    }
}
