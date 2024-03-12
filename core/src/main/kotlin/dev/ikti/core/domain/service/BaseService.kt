package dev.ikti.core.domain.service

interface BaseService<in Params, out T> {
    fun execute(params: Params): T
}
