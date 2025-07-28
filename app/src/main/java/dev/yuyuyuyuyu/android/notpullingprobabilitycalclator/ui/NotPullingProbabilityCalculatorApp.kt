package dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui

import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.di.notPullingProbabilityCalculatorAppModule
import dev.yuyuyuyuyu.android.notpullingprobabilitycalclator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreenImpl
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun NotPullingProbabilityCalculatorApp() {
    val backStack = rememberSaveableBackStack(root = NotPullingProbabilityCalculatorScreenImpl)
    val navigator = rememberCircuitNavigator(backStack) {}

    KoinApplication(
        application = {
            printLogger()
            modules(notPullingProbabilityCalculatorAppModule)
        },
    ) {
        MyMaterialTheme {
            CircuitCompositionLocals(circuit = koinInject()) {
                NavigableCircuitContent(navigator, backStack)
            }
        }
    }
}
