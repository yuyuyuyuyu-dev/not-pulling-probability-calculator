package dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.di

import com.slack.circuit.foundation.Circuit
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreenImpl
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.openSourceLicenseList.OpenSourceLicenseListScreenImpl
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculator
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorPresenter
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreen
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.openSourceLicenseList.OpenSourceLicenseList
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.openSourceLicenseList.OpenSourceLicenseListPresenter
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.openSourceLicenseList.OpenSourceLicenseListScreen
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

            .addUi<OpenSourceLicenseListScreenImpl, OpenSourceLicenseListScreen.State> { state, modifier ->
                OpenSourceLicenseList()
            }
            .addPresenter<OpenSourceLicenseListScreenImpl, OpenSourceLicenseListScreen.State>(
                presenter = OpenSourceLicenseListPresenter()
            )

            .build()
    }
}
