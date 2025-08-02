package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.useCases

import androidx.annotation.IntRange
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.models.Probability
import kotlin.math.pow

class CalculateNotPullingProbabilityUseCase {
    operator fun invoke(@IntRange(from = 0, to = 100) odds: Double, numberOfTrials: Int) = Probability(
        notPulling = (1 - odds / 100).pow(numberOfTrials),
    )
}
