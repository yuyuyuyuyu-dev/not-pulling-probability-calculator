package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.useCases

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.errors.DomainError
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.models.Probability
import kotlin.math.pow

class CalculateNotPullingProbabilityUseCase {
    operator fun invoke(odds: Double, numberOfTrials: Int): Result<Probability, DomainError> {
        if (odds !in 0.0..100.0) {
            return Err(DomainError.InvalidOddsError)
        }

        return Ok(
            value = Probability(
                notPulling = (1 - odds / 100).pow(numberOfTrials),
            ),
        )
    }
}
