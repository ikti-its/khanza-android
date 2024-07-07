package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.OrganisasiResponse
import dev.ikti.kehadiran.domain.repository.PresensiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PresensiGetLokasiUseCase @Inject constructor(
    private val presensiRepository: PresensiRepository
) {
    suspend fun execute(
        token: String
    ): Flow<BaseResponse<OrganisasiResponse>> {
        return try {
            presensiRepository.getLokasi(token)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
