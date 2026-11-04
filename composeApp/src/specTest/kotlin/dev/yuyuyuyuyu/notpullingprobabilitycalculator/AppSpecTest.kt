package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

@OptIn(ExperimentalTestApi::class)
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
    fun `shows the probabilities for 0_75 percent odds and 300 trials`() =
        runComposeUiTest {
            // Arrange
            setContent { App() }

            // Act
            onNodeWithTag("oddsTextField").performTextInput("0.75")
            onNodeWithTag("trialsTextField").performTextInput("300")

            // Assert
            assertTextIsDisplayed("Approx. 10.45092258072379 %")
            assertTextIsDisplayed("Approx. 89.54907741927622 %")
        }

    private suspend fun ComposeUiTest.assertTextIsDisplayed(text: String) {
        val deadline = TimeSource.Monotonic.markNow() + 10.seconds
        while (onAllNodesWithText(text, useUnmergedTree = true).fetchSemanticsNodes().isEmpty()) {
            if (deadline.hasPassedNow()) fail("\"$text\" was not shown within 10 seconds")
            withContext(Dispatchers.Default) { delay(50.milliseconds) }
            awaitIdle()
        }
        onNodeWithText(text, useUnmergedTree = true).assertIsDisplayed()
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
