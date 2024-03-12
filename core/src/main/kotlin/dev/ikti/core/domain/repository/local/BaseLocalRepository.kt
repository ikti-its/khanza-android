package dev.ikti.core.domain.repository.local

interface BaseLocalRepository<T> {
    suspend fun insert(data: T)
    suspend fun update(data: T)
    suspend fun delete(data: T)
}
