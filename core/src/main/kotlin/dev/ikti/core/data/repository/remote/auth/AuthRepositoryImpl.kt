package dev.ikti.core.data.repository.remote.auth

import dev.ikti.core.data.model.auth.LoginRequest
import dev.ikti.core.data.model.auth.LoginResponse
import dev.ikti.core.data.remote.AuthService
import dev.ikti.core.domain.repository.remote.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun login(request: LoginRequest): Flow<LoginResponse> {
        return flow {
            emit(authService.login(request))
        }
    }
}
