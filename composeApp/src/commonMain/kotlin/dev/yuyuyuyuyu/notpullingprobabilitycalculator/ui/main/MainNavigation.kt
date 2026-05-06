package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreen
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.openSourceLicenses.OpenSourceLicensesScreen

@Composable
fun MainNavigation(
    backStack: MutableList<MainNavigationRoute>,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                MainNavigationRoute.NotPullingProbabilityCalculator -> {
                    NavEntry(key) {
                        NotPullingProbabilityCalculatorScreen(TODO())
                    }
                }

                MainNavigationRoute.OpenSourceLicenses -> {
                    NavEntry(key) {
                        OpenSourceLicensesScreen()
                    }
                }
            }
        },
    )
}
