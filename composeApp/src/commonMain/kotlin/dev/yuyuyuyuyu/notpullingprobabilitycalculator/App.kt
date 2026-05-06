package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.di.AppComponent
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.di.createAppComponent
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.main.MainScreen
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar
import org.jetbrains.compose.resources.painterResource

import notpullingprobabilitycalculator.composeapp.generated.resources.Res
import notpullingprobabilitycalculator.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    val appComponent = remember { createAppComponent() }

    MyMaterialTheme {
        MainScreen(appComponent = appComponent)
    }
}