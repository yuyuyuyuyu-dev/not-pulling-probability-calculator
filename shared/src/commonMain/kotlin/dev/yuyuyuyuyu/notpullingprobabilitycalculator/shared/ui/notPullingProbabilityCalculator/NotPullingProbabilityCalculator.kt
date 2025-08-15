package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import notpullingprobabilitycalculator.shared.generated.resources.Res
import notpullingprobabilitycalculator.shared.generated.resources.not_pulling_probability_text
import notpullingprobabilitycalculator.shared.generated.resources.not_pulling_probability_value
import notpullingprobabilitycalculator.shared.generated.resources.number_of_trials_label
import notpullingprobabilitycalculator.shared.generated.resources.number_of_trials_placeholder
import notpullingprobabilitycalculator.shared.generated.resources.odds_label
import notpullingprobabilitycalculator.shared.generated.resources.odds_placeholder
import notpullingprobabilitycalculator.shared.generated.resources.pulling_probability_text
import notpullingprobabilitycalculator.shared.generated.resources.pulling_probability_value
import org.jetbrains.compose.resources.stringResource

@Composable
fun NotPullingProbabilityCalculator(
    state: NotPullingProbabilityCalculatorScreen.State,
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    Column(
        modifier = Modifier.width(IntrinsicSize.Max),
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
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(Res.string.odds_label)) },
            placeholder = { Text(stringResource(Res.string.odds_placeholder)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            maxLines = 1,
        )

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
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(Res.string.number_of_trials_label)) },
            placeholder = { Text(stringResource(Res.string.number_of_trials_placeholder)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            maxLines = 1,
        )

        Column {
            Text(stringResource(Res.string.not_pulling_probability_text))
            Text(
                stringResource(
                    Res.string.not_pulling_probability_value,
                    state.notPullingProbability,
                )
            )

            Spacer(Modifier.height(16.dp))

            Text(stringResource(Res.string.pulling_probability_text))
            Text(stringResource(Res.string.pulling_probability_value, state.pullingProbability))
        }
    }
}
