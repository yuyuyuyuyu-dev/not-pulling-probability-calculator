package dev.yuyuyuyuyu.android.notpullingprobabilitycalclator

import android.app.Application
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.di.notPullingProbabilityCalculatorAppModule
import org.koin.core.context.startKoin

class NotPullingProbabilityCalculatorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger()
            modules(notPullingProbabilityCalculatorAppModule)
        }
    }
}
