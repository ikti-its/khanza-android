package dev.ikti.profile.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.profile.data.model.ProfileRequest
import dev.ikti.profile.data.model.ProfileResponse
import dev.ikti.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend fun execute(
        token: String,
        akun: String,
        user: ProfileRequest
    ): Flow<BaseResponse<ProfileResponse>> {
        return try {
            profileRepository.update(token, akun, user)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}