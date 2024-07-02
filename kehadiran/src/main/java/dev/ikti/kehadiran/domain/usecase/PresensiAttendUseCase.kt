package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.AttendRequest
import dev.ikti.kehadiran.data.model.PresensiResponse
import dev.ikti.kehadiran.domain.repository.PresensiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PresensiAttendUseCase @Inject constructor(
    private val presensiRepository: PresensiRepository
) {
    suspend fun execute(
        token: String,
        attend: AttendRequest
    ): Flow<BaseResponse<PresensiResponse>> {
        return try {
            presensiRepository.attend(token, attend)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
