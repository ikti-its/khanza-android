package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.PengajuanResponse
import dev.ikti.kehadiran.domain.repository.PengajuanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PengajuanGetByIdUseCase @Inject constructor(
    private val pengajuanRepository: PengajuanRepository
) {
    suspend fun execute(
        token: String,
        id: String
    ): Flow<BaseResponse<PengajuanResponse>> {
        return try {
            pengajuanRepository.getById(token, id)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
