package dev.ikti.auth.data.repository

import dev.ikti.auth.data.model.LoginRequest
import dev.ikti.auth.data.model.LoginResponse
import dev.ikti.auth.data.remote.AuthService
import dev.ikti.auth.domain.repository.AuthRepository
import dev.ikti.core.data.model.BaseResponse
import dev.ikti.core.util.NetworkException
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
                    401 -> throw NetworkException.UnauthorizedException
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownException
                }
            }
        }
    }
}
