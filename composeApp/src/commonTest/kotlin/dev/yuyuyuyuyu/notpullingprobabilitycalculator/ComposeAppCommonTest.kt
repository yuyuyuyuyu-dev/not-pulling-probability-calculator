package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

fun hasTestTagStartingWith(prefix: String): SemanticsMatcher =
    SemanticsMatcher("TestTag starts with '$prefix'") { node ->
        val testTag = node.config.getOrNull(SemanticsProperties.TestTag)
        testTag?.startsWith(prefix) ?: false
    }

class ComposeAppCommonTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun calculate_probability() =
        runComposeUiTest {
            setContent { App() }

            onNode(hasTestTag("OddsTextField"), useUnmergedTree = true).performTextReplacement("0.75")
            onNode(hasTestTag("TrialsTextField"), useUnmergedTree = true).performTextReplacement("300")

            waitUntil(timeoutMillis = 5000) {
                onAllNodes(hasTestTagStartingWith("notPullingProbability_10.45092"), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }

            onNode(hasTestTagStartingWith("notPullingProbability_10.45092"), useUnmergedTree = true).assertExists()
            onNode(hasTestTagStartingWith("pullingProbability_89.54907"), useUnmergedTree = true).assertExists()
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
                onAllNodes(hasTestTag("openSourceLicensesMenuItemText"), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }

            onNode(hasTestTag("openSourceLicensesMenuItemText"), useUnmergedTree = true).onParent().performClick()

            waitUntil(timeoutMillis = 5000) {
                onAllNodes(hasContentDescription("navigate back"), useUnmergedTree = true)
                    .fetchSemanticsNodes().isNotEmpty()
            }

            onNode(hasContentDescription("navigate back"), useUnmergedTree = true).onParent().assertExists()
        }
}
