package dev.ikti.core.domain.usecase

interface BaseSuspendUseCase<in Params, out T> {
    suspend fun execute(params: Params): T
}
