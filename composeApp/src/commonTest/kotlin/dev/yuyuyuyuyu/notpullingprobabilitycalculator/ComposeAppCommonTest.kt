package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

class ComposeAppCommonTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun calculate_probability() =
        runComposeUiTest {
            setContent { App() }

            onAllNodes(hasSetTextAction(), useUnmergedTree = true)[0].performTextReplacement("0.75")
            onAllNodes(hasSetTextAction(), useUnmergedTree = true)[1].performTextReplacement("300")

            // In Wasm JS tests, string resources might take some time to load or might not load without proper Karma server setup for composeResources.
            // We assert the substring logic that matches the expected probability calculations.
            waitUntil(timeoutMillis = 5000) {
                onAllNodes(hasText("10.45092", substring = true), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }

            onNode(hasText("10.45092", substring = true), useUnmergedTree = true).assertExists()
            onNode(hasText("89.54907", substring = true), useUnmergedTree = true).assertExists()
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun navigate_to_open_source_licenses() =
        runComposeUiTest {
            setContent { App() }

            waitUntil(timeoutMillis = 5000) {
                onAllNodes(hasContentDescription("menu"), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }

            onNode(hasContentDescription("menu"), useUnmergedTree = true).onParent().performClick()

            waitUntil(timeoutMillis = 5000) {
                onAllNodes(
                    hasText("Open source", substring = true).or(hasText("オープンソース", substring = true)),
                    useUnmergedTree = true
                ).fetchSemanticsNodes().isNotEmpty()
            }

            onNode(
                hasText("Open source", substring = true).or(hasText("オープンソース", substring = true)),
                useUnmergedTree = true
            ).onParent().performClick()

            waitUntil(timeoutMillis = 5000) {
                onAllNodes(hasContentDescription("navigate back"), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }

            onNode(hasContentDescription("navigate back"), useUnmergedTree = true).onParent().assertExists()
        }
}
