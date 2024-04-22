package dev.ikti.auth.data.remote

import dev.ikti.auth.data.model.LoginRequest
import dev.ikti.auth.data.model.LoginResponse
import dev.ikti.core.data.model.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): BaseResponse<LoginResponse>
}
