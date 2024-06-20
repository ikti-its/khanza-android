package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.domain.repository.KehadiranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KehadiranGetJadwalByPegawaiIdUseCase @Inject constructor(
    private val kehadiranRepository: KehadiranRepository
) {
    suspend fun execute(
        token: String,
        id: String
    ): Flow<BaseResponse<List<JadwalResponse>>> {
        return try {
            kehadiranRepository.getJadwalByPegawaiId(token, id)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
