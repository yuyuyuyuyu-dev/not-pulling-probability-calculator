package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.di.LocalAppComponent
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.di.createAppComponent
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.main.MainScreen

@Composable
fun App() {
    val appComponent = remember { createAppComponent() }

    CompositionLocalProvider(LocalAppComponent provides appComponent) {
        MyMaterialTheme {
            MainScreen()
        }
    }
}

@Composable
@Preview
private fun AppPreview() {
    App()
}
