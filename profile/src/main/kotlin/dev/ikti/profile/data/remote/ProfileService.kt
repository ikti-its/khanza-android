package dev.ikti.profile.data.remote

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.profile.data.model.ProfileRequest
import dev.ikti.profile.data.model.ProfileResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileService {
    @PUT("m/profile/{akun}")
    suspend fun update(
        @Header("Authorization") token: String,
        @Path("akun") akun: String,
        @Body user: ProfileRequest,
    ): BaseResponse<ProfileResponse>
}
