package dev.yuyuyuyuyu.notpullingprobabilitycalculator.domain.models

data class Probability(
    val notPulling: Double,
) {
    val pulling = 1 - notPulling
}
