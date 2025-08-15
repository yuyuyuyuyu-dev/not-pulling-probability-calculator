package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.useCases

import com.github.michaelbull.result.get
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

        val actual = useCase(odds, numberOfTrials).get()

        assertEquals(expected, actual)
    }

    @Test
    fun `invoke returns 1_0 when odds are 0`() {
        val odds = 0.0
        val numberOfTrials = 149
        val expected = Probability(notPulling = 1.0)

        val actual = useCase(odds, numberOfTrials).get()

        assertEquals(expected, actual)
    }

    @Test
    fun `invoke returns 0_0 when odds are 100`() {
        val odds = 100.0
        val numberOfTrials = 149
        val expected = Probability(notPulling = 0.0)

        val actual = useCase(odds, numberOfTrials).get()

        assertEquals(expected, actual)
    }
}
