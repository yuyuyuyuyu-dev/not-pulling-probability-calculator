package dev.yuyuyuyuyu.notpullingprobabilitycalculator.di

import com.slack.circuit.foundation.Circuit
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculator
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorPresenter
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreen
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.openSourceLicenseList.OpenSourceLicenseList
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.openSourceLicenseList.OpenSourceLicenseListPresenter
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.openSourceLicenseList.OpenSourceLicenseListScreen
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreenImpl
import org.koin.dsl.module

val uiModule = module {
    single {
        Circuit.Builder()
            .addUi<NotPullingProbabilityCalculatorScreenImpl, NotPullingProbabilityCalculatorScreen.State> { state, modifier ->
                NotPullingProbabilityCalculator(
                    state = state,
                    modifier = modifier,
                )
            }
            .addPresenter<NotPullingProbabilityCalculatorScreenImpl, NotPullingProbabilityCalculatorScreen.State>(
                presenter = NotPullingProbabilityCalculatorPresenter(
                    calculateNotPullingProbabilityUseCase = get(),
                )
            )

            .addUi<OpenSourceLicenseListScreen, OpenSourceLicenseListScreen.State> { _, modifier ->
                OpenSourceLicenseList(modifier)
            }
            .addPresenter<OpenSourceLicenseListScreen, OpenSourceLicenseListScreen.State>(
                presenter = OpenSourceLicenseListPresenter()
            )

            .build()
    }
}
