package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.useCases

import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.models.Probability
import kotlin.math.pow

class CalculateNotPullingProbabilityUseCase {
    operator fun invoke(odds: Double, numberOfTrials: Int) = Probability(
        notPulling = (1 - odds / 100).pow(numberOfTrials),
    )
}
