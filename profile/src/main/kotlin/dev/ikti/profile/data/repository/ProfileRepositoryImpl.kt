package dev.ikti.profile.data.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.profile.data.model.ProfileRequest
import dev.ikti.profile.data.model.ProfileResponse
import dev.ikti.profile.data.remote.ProfileService
import dev.ikti.profile.domain.repository.ProfileRepository
import dev.ikti.profile.util.ProfileException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val profileService: ProfileService
) : ProfileRepository {
    override suspend fun update(
        token: String,
        akun: String,
        user: ProfileRequest
    ): Flow<BaseResponse<ProfileResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(profileService.update(bearer, akun, user))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw ProfileException.AccountNotFoundException
                }
            }
        }
    }
}
