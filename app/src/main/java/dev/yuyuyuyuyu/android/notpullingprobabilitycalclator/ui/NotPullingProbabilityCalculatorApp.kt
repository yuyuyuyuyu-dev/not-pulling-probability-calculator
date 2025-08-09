package dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.R
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreenImpl
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.openSourceLicenseList.OpenSourceLicenseListScreenImpl
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialDynamicTheme
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.openSourceLicenseList.OpenSourceLicenseListScreen
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar
import org.koin.compose.koinInject

@Composable
fun NotPullingProbabilityCalculatorApp() {
    val backStack = rememberSaveableBackStack(root = NotPullingProbabilityCalculatorScreenImpl)
    val navigator = rememberCircuitNavigator(backStack) {}

    val focusManager = LocalFocusManager.current
    val uriHandler = LocalUriHandler.current

    MyMaterialDynamicTheme {
        Scaffold(
            modifier = Modifier.clickable(
                interactionSource = null,
                indication = null,
                onClick = { focusManager.clearFocus() },
            ),
            topBar = {
                val currentScreen = backStack.topRecord?.screen

                SimpleTopAppBar(
                    title = when (currentScreen) {
                        is OpenSourceLicenseListScreen -> stringResource(R.string.open_source_licenses)
                        else -> stringResource(R.string.app_name)
                    },
                    navigateBackIsPossible = backStack.size > 1,
                    onNavigateBackButtonClick = { navigator.pop() },
                    onOpenSourceLicensesButtonClick = {
                        navigator.goTo(OpenSourceLicenseListScreenImpl)
                    },
                    onSourceCodeButtonClick = {
                        uriHandler.openUri("https://github.com/yuyuyuyuyu-dev/not-pulling-probability-calculator")
                    }
                )
            },
        ) { innerPadding ->
            CircuitCompositionLocals(circuit = koinInject()) {
                NavigableCircuitContent(navigator, backStack, Modifier.padding(innerPadding))
            }
        }
    }
}
