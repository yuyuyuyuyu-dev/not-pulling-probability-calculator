package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar
import notpullingprobabilitycalculator.composeapp.generated.resources.Res
import notpullingprobabilitycalculator.composeapp.generated.resources.app_name
import notpullingprobabilitycalculator.composeapp.generated.resources.open_source_licenses
import org.jetbrains.compose.resources.stringResource
import kotlin.compareTo

@Composable
fun MainScreen() {
    val backStack: MutableList<MainNavigationRoute> =
        rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(MainNavigationRoute.NotPullingProbabilityCalculator)
        }

    val focusManager = LocalFocusManager.current
    val uriHandler = LocalUriHandler.current

    Scaffold(
        modifier = Modifier.clickable(
            interactionSource = null,
            indication = null,
            onClick = { focusManager.clearFocus() },
        ),
        topBar = {
            SimpleTopAppBar(
                title =
                    when (backStack.lastOrNull()) {
                        is MainNavigationRoute.OpenSourceLicenses -> stringResource(Res.string.open_source_licenses)
                        else -> stringResource(Res.string.app_name)
                    },
                navigateBackIsPossible = backStack.size > 1,
                onNavigateBackButtonClick = { backStack.removeLastOrNull() },
                onOpenSourceLicensesButtonClick = {
                    if (backStack.lastOrNull() != MainNavigationRoute.OpenSourceLicenses) {
                        backStack.add(MainNavigationRoute.OpenSourceLicenses)
                    }
                },
                onSourceCodeButtonClick = {
                    uriHandler.openUri("https://github.com/yuyuyuyuyu-dev/not-pulling-probability-calculator")
                },
            )
        },
    ) { innerPadding ->
        MainNavigation(backStack, Modifier.padding(innerPadding))
    }
}
