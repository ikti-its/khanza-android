package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.domain.repository.PresensiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PresensiGetUseCase @Inject constructor(
    private val presensiRepository: PresensiRepository
) {
    suspend fun execute(
        token: String,
        hari: Int
    ): Flow<BaseResponse<List<JadwalResponse>>> {
        return try {
            presensiRepository.get(token, hari)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
