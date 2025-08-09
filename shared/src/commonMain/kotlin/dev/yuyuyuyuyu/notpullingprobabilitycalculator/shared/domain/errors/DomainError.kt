package dev.yuyuyuyuyu.notpullingprobabilitycalculator.shared.domain.errors

sealed interface DomainError {
    val message: String

    data object InvalidOddsError : DomainError {
        override val message = "odds must be between 0 and 100"
    }
}
