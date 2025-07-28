package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotPullingProbabilityCalculator(
    state: NotPullingProbabilityCalculatorScreen.State,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    var odds by rememberSaveable { mutableStateOf("") }
    var numberOfTrials by rememberSaveable { mutableStateOf("") }

    TextField(
        value = odds,
        onValueChange = {
            odds = it
            state.eventSink(
                NotPullingProbabilityCalculatorScreen.Event.ChangeInputValue(
                    odds = it.toDoubleOrNull() ?: 0.0,
                    numberOfTrials = numberOfTrials.toIntOrNull() ?: 0,
                )
            )
        },
        label = { Text("排出率（単位：%）") },
    )

    Spacer(Modifier.height(16.dp))

    TextField(
        value = numberOfTrials,
        onValueChange = {
            numberOfTrials = it
            state.eventSink(
                NotPullingProbabilityCalculatorScreen.Event.ChangeInputValue(
                    odds = odds.toDoubleOrNull() ?: 0.0,
                    numberOfTrials = it.toIntOrNull() ?: 0,
                )
            )
        },
        label = { Text("試行回数（単位：回）") },
    )

    Spacer(Modifier.height(48.dp))

    Column {
        Text("一度も引けない確率")
        Text("約 ${state.notPullingProbability} %")

        Spacer(Modifier.height(16.dp))

        Text("少なくとも1回は引ける確率")
        Text("約 ${state.pullingProbability} %")
    }
}
