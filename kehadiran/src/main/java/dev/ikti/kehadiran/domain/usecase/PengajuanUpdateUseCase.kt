package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.PengajuanRequest
import dev.ikti.kehadiran.data.model.PengajuanResponse
import dev.ikti.kehadiran.domain.repository.PengajuanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PengajuanUpdateUseCase @Inject constructor(
    private val pengajuanRepository: PengajuanRepository
) {
    suspend fun execute(
        token: String,
        id: String,
        ajuan: PengajuanRequest
    ): Flow<BaseResponse<PengajuanResponse>> {
        return try {
            pengajuanRepository.update(token, id, ajuan)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
