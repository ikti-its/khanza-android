package dev.ikti.core.domain.repository.remote.auth

import dev.ikti.core.data.model.auth.LoginRequest
import dev.ikti.core.data.model.auth.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(request: LoginRequest): Flow<LoginResponse>
}
