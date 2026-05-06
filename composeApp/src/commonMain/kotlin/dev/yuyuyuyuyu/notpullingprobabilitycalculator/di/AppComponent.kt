package dev.yuyuyuyuyu.notpullingprobabilitycalculator.di

import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorViewModel
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorViewModelImpl
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class AppComponent {
    
    abstract val notPullingProbabilityCalculatorViewModel: NotPullingProbabilityCalculatorViewModelImpl
    
    @Provides
    fun provideNotPullingProbabilityCalculatorViewModel(
        impl: NotPullingProbabilityCalculatorViewModelImpl
    ): NotPullingProbabilityCalculatorViewModel = impl
}

expect fun createAppComponent(): AppComponent
