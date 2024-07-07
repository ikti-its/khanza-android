package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.FotoPegawaiResponse
import dev.ikti.kehadiran.domain.repository.PresensiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PresensiGetFotoUseCase @Inject constructor(
    private val presensiRepository: PresensiRepository
) {
    suspend fun execute(
        token: String,
        id: String
    ): Flow<BaseResponse<FotoPegawaiResponse>> {
        return try {
            presensiRepository.getFoto(token, id)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
