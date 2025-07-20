package dev.yuyuyuyuyu.notpullingprobabilitycalculator.di

import org.koin.dsl.module

val notPullingProbabilityCalculatorModule = module {
    includes(uiModule, domainModule)
}
