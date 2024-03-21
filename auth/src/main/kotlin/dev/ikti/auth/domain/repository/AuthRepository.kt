package dev.ikti.auth.domain.repository

import dev.ikti.auth.data.model.LoginRequest
import dev.ikti.auth.data.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(request: LoginRequest): Flow<LoginResponse>
}
