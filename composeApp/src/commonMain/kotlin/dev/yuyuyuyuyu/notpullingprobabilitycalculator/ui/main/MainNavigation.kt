package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.di.LocalAppComponent
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreen
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.openSourceLicenses.OpenSourceLicensesScreen

@Composable
fun MainNavigation(
    backStack: SnapshotStateList<MainNavigationRoute>,
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
                        // Resolve this screen's ViewModel where it is actually used,
                        // pulling the container from the composition instead of having
                        // it threaded down as a parameter.
                        val appComponent = LocalAppComponent.current
                        NotPullingProbabilityCalculatorScreen(
                            viewModel = viewModel { appComponent.notPullingProbabilityCalculatorViewModel },
                        )
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
