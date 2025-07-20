package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui

import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.di.notPullingProbabilityCalculatorModule
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
@Preview
fun NotPullingProbabilityCalculatorApp() {
    val backStack = rememberSaveableBackStack(root = NotPullingProbabilityCalculatorScreen)
    val navigator = rememberCircuitNavigator(backStack) {}

    KoinApplication(
        application = {
            printLogger()
            modules(notPullingProbabilityCalculatorModule)
        },
    ) {
        MyMaterialTheme {
            CircuitCompositionLocals(circuit = koinInject()) {
                NavigableCircuitContent(navigator, backStack)
            }
        }
    }
}
