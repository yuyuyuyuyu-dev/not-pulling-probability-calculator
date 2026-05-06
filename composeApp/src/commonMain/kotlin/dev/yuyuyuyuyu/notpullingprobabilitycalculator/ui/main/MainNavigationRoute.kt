package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.main

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface MainNavigationRoute : NavKey {
    @Serializable
    data object NotPullingProbabilityCalculator : MainNavigationRoute

    @Serializable
    data object OpenSourceLicenses : MainNavigationRoute
}
