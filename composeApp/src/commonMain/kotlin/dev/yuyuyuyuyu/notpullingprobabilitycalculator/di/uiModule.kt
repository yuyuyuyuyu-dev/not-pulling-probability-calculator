package dev.yuyuyuyuyu.notpullingprobabilitycalculator.di

import com.slack.circuit.foundation.Circuit
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculator
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorPresenter
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreen
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.openSourceLicenseList.OpenSourceLicenseList
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.openSourceLicenseList.OpenSourceLicenseListPresenter
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.openSourceLicenseList.OpenSourceLicenseListScreen
import org.koin.dsl.module

val uiModule = module {
    single {
        Circuit.Builder()
            .addUi<NotPullingProbabilityCalculatorScreen, NotPullingProbabilityCalculatorScreen.State> { state, modifier ->
                NotPullingProbabilityCalculator()
            }
            .addPresenter<NotPullingProbabilityCalculatorScreen, NotPullingProbabilityCalculatorScreen.State>(
                presenter = NotPullingProbabilityCalculatorPresenter()
            )

            .addUi<OpenSourceLicenseListScreen, OpenSourceLicenseListScreen.State> { state, modifier ->
                OpenSourceLicenseList()
            }
            .addPresenter<OpenSourceLicenseListScreen, OpenSourceLicenseListScreen.State>(
                presenter = OpenSourceLicenseListPresenter()
            )

            .build()
    }
}
