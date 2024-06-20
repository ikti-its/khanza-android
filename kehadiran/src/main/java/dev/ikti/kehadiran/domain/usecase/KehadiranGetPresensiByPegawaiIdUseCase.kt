package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.PresensiResponse
import dev.ikti.kehadiran.domain.repository.KehadiranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KehadiranGetPresensiByPegawaiIdUseCase @Inject constructor(
    private val kehadiranRepository: KehadiranRepository
) {
    suspend fun execute(
        token: String,
        id: String
    ): Flow<BaseResponse<List<PresensiResponse>>> {
        return try {
            kehadiranRepository.getPresensiByPegawaiId(token, id)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
