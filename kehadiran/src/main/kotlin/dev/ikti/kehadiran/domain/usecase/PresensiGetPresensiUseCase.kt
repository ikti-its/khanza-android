package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.StatusPresensiResponse
import dev.ikti.kehadiran.domain.repository.PresensiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PresensiGetPresensiUseCase @Inject constructor(
    private val presensiRepository: PresensiRepository
) {
    suspend fun execute(
        token: String,
        id: String
    ): Flow<BaseResponse<StatusPresensiResponse>> {
        return try {
            presensiRepository.getPresensi(token, id)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
