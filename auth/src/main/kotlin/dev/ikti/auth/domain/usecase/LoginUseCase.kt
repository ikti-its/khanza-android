package dev.ikti.auth.domain.usecase

import dev.ikti.auth.data.model.LoginRequest
import dev.ikti.auth.data.model.LoginResponse
import dev.ikti.auth.domain.repository.AuthRepository
import dev.ikti.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseSuspendUseCase<LoginRequest, Flow<LoginResponse>> {
    override suspend fun execute(params: LoginRequest): Flow<LoginResponse> {
        return try {
            authRepository.login(params)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
