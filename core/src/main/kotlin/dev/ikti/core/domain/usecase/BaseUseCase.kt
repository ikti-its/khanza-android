package dev.ikti.core.domain.usecase

interface BaseUseCase<in Params, out T> {
    fun execute(params: Params): T
}
