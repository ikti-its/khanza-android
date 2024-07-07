package dev.ikti.pegawai.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.pegawai.data.model.OrganisasiResponse
import dev.ikti.pegawai.domain.repository.PegawaiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLokasiUseCase @Inject constructor(
    private val pegawaiRepository: PegawaiRepository
) {
    suspend fun execute(
        token: String
    ): Flow<BaseResponse<OrganisasiResponse>> {
        return try {
            pegawaiRepository.getLokasi(token)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
