package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mikepenz.aboutlibraries.ui.compose.produceLibraries
import dev.yuyuyuyuyu.mycomposables.MyScaffold
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.di.LocalAppComponent
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator.NotPullingProbabilityCalculatorScreen
import notpullingprobabilitycalculator.composeapp.generated.resources.Res
import notpullingprobabilitycalculator.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current
    val uriHandler = LocalUriHandler.current

    val libraries by produceLibraries {
        Res.readBytes("files/aboutlibraries.json").decodeToString()
    }
    val deduplicatedLibraries =
        libraries?.let { libs ->
            libs.copy(libraries = libs.libraries.distinctBy { it.name })
        }

    MyScaffold(
        title = stringResource(Res.string.app_name),
        libraries = deduplicatedLibraries,
        modifier =
            modifier.clickable(
                interactionSource = null,
                indication = null,
                onClick = { focusManager.clearFocus() },
            ),
        onSourceCodeButtonClick = {
            uriHandler.openUri("https://github.com/yuyuyuyuyu-dev/not-pulling-probability-calculator")
        },
    ) { innerPadding ->
        // Resolve this screen's ViewModel where it is actually used,
        // pulling the container from the composition instead of having
        // it threaded down as a parameter.
        val appComponent = LocalAppComponent.current
        NotPullingProbabilityCalculatorScreen(
            viewModel = viewModel { appComponent.notPullingProbabilityCalculatorViewModel },
            modifier = Modifier.padding(innerPadding),
        )
    }
}
