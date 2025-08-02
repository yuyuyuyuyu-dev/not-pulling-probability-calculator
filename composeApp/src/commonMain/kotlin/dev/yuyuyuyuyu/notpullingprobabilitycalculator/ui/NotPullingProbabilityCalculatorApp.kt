package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.di.notPullingProbabilityCalculatorAppModule
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.openSourceLicenseList.OpenSourceLicenseListScreen
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreenImpl
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.openSourceLicenseList.OpenSourceLicenseListScreenImpl
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
@Preview
fun NotPullingProbabilityCalculatorApp() {
    val backStack = rememberSaveableBackStack(root = NotPullingProbabilityCalculatorScreenImpl)
    val navigator = rememberCircuitNavigator(backStack) {}

    val focusManager = LocalFocusManager.current
    val uriHandler = LocalUriHandler.current

    KoinApplication(
        application = {
            printLogger()
            modules(notPullingProbabilityCalculatorAppModule)
        },
    ) {
        MyMaterialTheme {
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
                            is OpenSourceLicenseListScreen -> "オープンソース ライセンス"
                            else -> "引けない確率の計算"
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
                }
            ) { innerPadding ->
                CircuitCompositionLocals(circuit = koinInject()) {
                    NavigableCircuitContent(navigator, backStack, Modifier.padding(innerPadding))
                }
            }
        }
    }
}
