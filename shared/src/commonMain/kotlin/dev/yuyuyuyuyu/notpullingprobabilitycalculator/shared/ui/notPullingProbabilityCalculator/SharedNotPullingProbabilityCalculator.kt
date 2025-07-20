package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.ui.notPullingProbabilityCalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SharedNotPullingProbabilityCalculator(
    state: NotPullingProbabilityCalculatorScreen.State,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    TextField(
        value = "",
        onValueChange = {},
        label = { Text("排出率（単位：%）") },
    )

    Spacer(Modifier.height(16.dp))

    TextField(
        value = "",
        onValueChange = {},
        label = { Text("試行回数（単位：回）") },
    )

    Spacer(Modifier.height(48.dp))

    Column {
        Text("一度も引けない確率")
        Text("約 100 %")

        Spacer(Modifier.height(16.dp))

        Text("少なくとも1回は引ける確率")
        Text("約 0 %")
    }
}
