package dev.ikti.auth.data.repository

import dev.ikti.auth.data.model.LoginRequest
import dev.ikti.auth.data.model.LoginResponse
import dev.ikti.auth.data.remote.AuthService
import dev.ikti.auth.domain.repository.AuthRepository
import dev.ikti.auth.util.AuthException
import dev.ikti.core.data.model.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun login(request: LoginRequest): Flow<BaseResponse<LoginResponse>> {
        return flow {
            try {
                emit(authService.login(request))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    400 -> throw AuthException.EmailInvalidException
                    401 -> throw AuthException.PasswordIncorrectException
                    404 -> throw AuthException.AccountNotFoundException
                    else -> throw AuthException.FailedToLoginException
                }
            }
        }
    }
}
