package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun NotPullingProbabilityCalculator(
    state: NotPullingProbabilityCalculatorScreen.State,
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        var odds by rememberSaveable { mutableStateOf("") }
        var numberOfTrials by rememberSaveable { mutableStateOf("") }

        val focusManager = LocalFocusManager.current

        TextField(
            value = odds,
            onValueChange = {
                if (it.isNotEmpty() && it.toDoubleOrNull() == null) {
                    return@TextField
                }

                odds = it
                state.eventSink(
                    NotPullingProbabilityCalculatorScreen.Event.ChangeInputValue(
                        odds = it,
                        numberOfTrials = numberOfTrials,
                    )
                )
            },
            label = { Text("排出率（単位：%）") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            maxLines = 1,
        )

        // TODO: placeholder
        TextField(
            value = numberOfTrials,
            onValueChange = {
                if (it.isNotEmpty() && it.toIntOrNull() == null) {
                    return@TextField
                }

                numberOfTrials = it
                state.eventSink(
                    NotPullingProbabilityCalculatorScreen.Event.ChangeInputValue(
                        odds = odds,
                        numberOfTrials = it,
                    )
                )
            },
            label = { Text("試行回数（単位：回）") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            maxLines = 1,
        )

        Column {
            Text("一度も引けない確率")
            Text("約 ${state.notPullingProbability} %")

            Spacer(Modifier.height(16.dp))

            Text("少なくとも1回は引ける確率")
            Text("約 ${state.pullingProbability} %")
        }
    }
}
