package dev.ikti.pegawai.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.pegawai.data.model.KetersediaanResponse
import dev.ikti.pegawai.domain.repository.PegawaiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetKetersediaanUseCase @Inject constructor(
    private val pegawaiRepository: PegawaiRepository
) {
    suspend fun execute(
        token: String,
        tanggal: String
    ): Flow<BaseResponse<List<KetersediaanResponse>>> {
        return try {
            pegawaiRepository.getKetersediaan(token, tanggal)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
