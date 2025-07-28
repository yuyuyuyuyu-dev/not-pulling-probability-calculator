package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.di

import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.useCases.CalculateNotPullingProbabilityUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::CalculateNotPullingProbabilityUseCase)
}
