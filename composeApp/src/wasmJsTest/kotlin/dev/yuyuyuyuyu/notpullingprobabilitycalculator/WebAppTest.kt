package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlinx.browser.document
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.awaitPendingResourceStateLoads
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class, ExperimentalResourceApi::class)
class WebAppTest {
    @AfterTest
    fun tearDown() {
        restoreBrowserLanguage()
    }

    @Test
    fun `sets the page language to Japanese when the browser prefers Japanese`() =
        runComposeUiTest {
            // Arrange
            useBrowserLanguage("ja-JP")
            document.documentElement?.removeAttribute("lang")

            // Act
            setContent { WebApp() }
            waitForResources()

            // Assert
            assertEquals("ja", document.documentElement?.getAttribute("lang"))
        }

    @Test
    fun `sets the page language to English when the browser prefers English`() =
        runComposeUiTest {
            // Arrange
            useBrowserLanguage("en-US")
            document.documentElement?.removeAttribute("lang")

            // Act
            setContent { WebApp() }
            waitForResources()

            // Assert
            assertEquals("en", document.documentElement?.getAttribute("lang"))
        }

    @Test
    fun `sets the page language to English when the browser prefers a language without its own text`() =
        runComposeUiTest {
            // Arrange
            useBrowserLanguage("fr-FR")
            document.documentElement?.removeAttribute("lang")

            // Act
            setContent { WebApp() }
            waitForResources()

            // Assert
            assertEquals("en", document.documentElement?.getAttribute("lang"))
        }

    private suspend fun ComposeUiTest.waitForResources() {
        waitForIdle()
        awaitPendingResourceStateLoads()
        waitForIdle()
    }
}

@Suppress("UnusedParameter")
@OptIn(ExperimentalWasmJsInterop::class)
private fun useBrowserLanguage(languageTag: String): Unit =
    js(
        """
        {
            Object.defineProperty(navigator, 'languages', { get: () => [languageTag], configurable: true });
            window.dispatchEvent(new Event('languagechange'));
        }
        """,
    )

@OptIn(ExperimentalWasmJsInterop::class)
private fun restoreBrowserLanguage(): Unit =
    js(
        """
        {
            delete navigator.languages;
            window.dispatchEvent(new Event('languagechange'));
        }
        """,
    )
