package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import notpullingprobabilitycalculator.composeapp.generated.resources.Res
import notpullingprobabilitycalculator.composeapp.generated.resources.language_tag
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        WebApp()
    }
}

@Composable
fun WebApp() {
    val languageTag = stringResource(Res.string.language_tag)

    LaunchedEffect(languageTag) {
        document.documentElement?.setAttribute("lang", languageTag)
    }

    App()
}
