package dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreenImpl
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.openSourceLicenseList.OpenSourceLicenseListScreen
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar
import org.koin.compose.koinInject

@Composable
fun NotPullingProbabilityCalculatorApp() {
    val backStack = rememberSaveableBackStack(root = NotPullingProbabilityCalculatorScreenImpl)
    val navigator = rememberCircuitNavigator(backStack) {}

    val uriHandler = LocalUriHandler.current

    MyMaterialTheme {
        Scaffold(
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
                        navigator.goTo(OpenSourceLicenseListScreen)
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
