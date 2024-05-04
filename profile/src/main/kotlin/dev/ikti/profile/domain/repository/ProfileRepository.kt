package dev.ikti.profile.domain.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.profile.data.model.ProfileRequest
import dev.ikti.profile.data.model.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun update(
        token: String,
        akun: String,
        user: ProfileRequest
    ): Flow<BaseResponse<ProfileResponse>>
}
