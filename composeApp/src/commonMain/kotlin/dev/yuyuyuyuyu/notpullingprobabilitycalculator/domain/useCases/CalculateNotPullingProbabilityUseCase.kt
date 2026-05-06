package dev.yuyuyuyuyu.notpullingprobabilitycalculator.domain.useCases

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.domain.errors.DomainError
import dev.yuyuyuyuyu.notpullingprobabilitycalculator.domain.models.Probability
import me.tatarka.inject.annotations.Inject
import kotlin.math.pow

@Inject
class CalculateNotPullingProbabilityUseCase {
    operator fun invoke(
        odds: Double,
        numberOfTrials: Int,
    ): Result<Probability, DomainError> {
        if (odds !in 0.0..100.0) {
            return Err(DomainError.InvalidOddsError)
        }

        return Ok(
            value =
                Probability(
                    notPulling = (1 - odds / 100).pow(numberOfTrials),
                ),
        )
    }
}
