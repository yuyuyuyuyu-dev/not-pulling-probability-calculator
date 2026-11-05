package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.v2.runComposeUiTest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.awaitPendingResourceStateLoads
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class, ExperimentalResourceApi::class)
class AppSpecTest {
    @BeforeTest
    fun setUp() {
        useEnglishInBrowser()
    }

    @AfterTest
    fun tearDown() {
        restoreBrowserLanguage()
    }

    @Test
    fun `should show the probabilities for 0_75 percent odds and 300 trials`() =
        runComposeUiTest {
            // Arrange
            setContent { App() }
            waitForResources()

            // Act
            onNodeWithTag("oddsTextField").performTextInput("0.75")
            onNodeWithTag("trialsTextField").performTextInput("300")
            waitForResources()

            // Assert
            onNodeWithText("Approx. 10.45092258072379 %", useUnmergedTree = true).assertIsDisplayed()
            onNodeWithText("Approx. 89.54907741927622 %", useUnmergedTree = true).assertIsDisplayed()
        }

    private suspend fun ComposeUiTest.waitForResources() {
        waitForIdle()
        awaitPendingResourceStateLoads()
        waitForIdle()
    }
}

@OptIn(ExperimentalWasmJsInterop::class)
private fun useEnglishInBrowser(): Unit =
    js(
        """
        Object.defineProperty(navigator, 'languages', { get: () => ['en-US'], configurable: true })
        """,
    )

@OptIn(ExperimentalWasmJsInterop::class)
private fun restoreBrowserLanguage(): Unit = js("delete navigator.languages")
