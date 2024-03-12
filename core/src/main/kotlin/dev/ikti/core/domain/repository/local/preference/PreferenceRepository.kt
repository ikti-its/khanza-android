package dev.ikti.core.domain.repository.local.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    fun isNewUser(): Flow<Boolean>
    suspend fun setNewUser(isNew: Boolean)
    fun getUserToken(): Flow<String>
    suspend fun setUserToken(token: String)
    suspend fun clearUserToken()
}
