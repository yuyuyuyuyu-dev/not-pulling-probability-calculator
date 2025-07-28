package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.useCases

import dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.models.Probability
import kotlin.test.Test
import kotlin.test.assertEquals

class CalculateNotPullingProbabilityUseCaseTest {

    private val useCase = CalculateNotPullingProbabilityUseCase()

    @Test
    fun `invoke should return correct probability when not pulling 0_75 percent odds 300 times`() {
        val odds = 0.75
        val numberOfTrials = 300
        val expected = Probability(notPulling = 0.10450922580723788)

        val actual = useCase(odds, numberOfTrials)

        assertEquals(expected, actual)
    }
}
