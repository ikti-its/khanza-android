package dev.ikti.core.domain.usecase.remote.auth

import dev.ikti.core.data.model.auth.LoginRequest
import dev.ikti.core.data.model.auth.LoginResponse
import dev.ikti.core.domain.repository.remote.auth.AuthRepository
import dev.ikti.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : BaseSuspendUseCase<LoginRequest, Flow<LoginResponse>> {
    override suspend fun execute(params: LoginRequest): Flow<LoginResponse> {
        return authRepository.login(params)
    }
}
