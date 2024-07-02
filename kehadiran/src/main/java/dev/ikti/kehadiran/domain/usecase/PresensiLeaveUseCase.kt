package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.LeaveRequest
import dev.ikti.kehadiran.data.model.PresensiResponse
import dev.ikti.kehadiran.domain.repository.PresensiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PresensiLeaveUseCase @Inject constructor(
    private val presensiRepository: PresensiRepository
) {
    suspend fun execute(
        token: String,
        emergency: Boolean,
        leave: LeaveRequest
    ): Flow<BaseResponse<PresensiResponse>> {
        return try {
            presensiRepository.leave(token, emergency, leave)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
