package dev.yuyuyuyuyu.notpullingprobabilitycalculator.ui.notPullingProbabilityCalculator

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import notpullingprobabilitycalculator.composeapp.generated.resources.Res
import notpullingprobabilitycalculator.composeapp.generated.resources.not_pulling_probability_text
import notpullingprobabilitycalculator.composeapp.generated.resources.not_pulling_probability_value
import notpullingprobabilitycalculator.composeapp.generated.resources.number_of_trials_label
import notpullingprobabilitycalculator.composeapp.generated.resources.number_of_trials_placeholder
import notpullingprobabilitycalculator.composeapp.generated.resources.odds_label
import notpullingprobabilitycalculator.composeapp.generated.resources.odds_placeholder
import notpullingprobabilitycalculator.composeapp.generated.resources.pulling_probability_text
import notpullingprobabilitycalculator.composeapp.generated.resources.pulling_probability_value
import org.jetbrains.compose.resources.stringResource

@Composable
fun NotPullingProbabilityCalculatorScreen(
    viewModel: NotPullingProbabilityCalculatorViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val focusManager = LocalFocusManager.current

            TextField(
                value = uiState.odds,
                onValueChange = {
                    if (it.isNotEmpty() && it.toDoubleOrNull() == null) {
                        return@TextField
                    }
                    viewModel.onOddsChange(it)
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(Res.string.odds_label)) },
                placeholder = { Text(stringResource(Res.string.odds_placeholder)) },
                keyboardOptions =
                    KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    ),
                maxLines = 1,
            )

            TextField(
                value = uiState.numberOfTrials,
                onValueChange = {
                    if (it.isNotEmpty() && it.toIntOrNull() == null) {
                        return@TextField
                    }
                    viewModel.onNumberOfTrialsChange(it)
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(Res.string.number_of_trials_label)) },
                placeholder = { Text(stringResource(Res.string.number_of_trials_placeholder)) },
                keyboardOptions =
                    KeyboardOptions.Default.copy(
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
                        uiState.notPullingProbability,
                    ),
                )

                Spacer(Modifier.height(16.dp))

                Text(stringResource(Res.string.pulling_probability_text))
                Text(stringResource(Res.string.pulling_probability_value, uiState.pullingProbability))
            }
        }
    }
}
