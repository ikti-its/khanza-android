package dev.ikti.core.domain.service

interface BaseSuspendService<in Params, out T> {
    suspend fun execute(params: Params): T
}
