package dev.ikti.core.data.remote

import dev.ikti.core.data.model.auth.LoginRequest
import dev.ikti.core.data.model.auth.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
