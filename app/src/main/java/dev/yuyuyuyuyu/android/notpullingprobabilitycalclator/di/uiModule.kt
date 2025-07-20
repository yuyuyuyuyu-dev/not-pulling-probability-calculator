package dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.di

import com.slack.circuit.foundation.Circuit
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculator
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorPresenter
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreen
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.openSourceLicenseList.OpenSourceLicenseList
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.openSourceLicenseList.OpenSourceLicenseListPresenter
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.openSourceLicenseList.OpenSourceLicenseListScreen
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
