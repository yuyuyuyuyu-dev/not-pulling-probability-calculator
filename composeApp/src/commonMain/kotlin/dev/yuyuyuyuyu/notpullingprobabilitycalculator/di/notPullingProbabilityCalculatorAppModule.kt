package dev.yuyuyuyuyu.notpullingprobabilitycalculator.di

import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.di.domainModule
import org.koin.dsl.module

val notPullingProbabilityCalculatorAppModule = module {
    includes(uiModule, domainModule)
}
