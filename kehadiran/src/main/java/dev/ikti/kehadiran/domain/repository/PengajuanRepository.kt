package dev.ikti.kehadiran.domain.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.PengajuanRequest
import dev.ikti.kehadiran.data.model.PengajuanResponse
import kotlinx.coroutines.flow.Flow

interface PengajuanRepository {
    suspend fun create(
        token: String,
        ajuan: PengajuanRequest
    ): Flow<BaseResponse<PengajuanResponse>>

    suspend fun getAll(
        token: String
    ): Flow<BaseResponse<List<PengajuanResponse>>>

    suspend fun getById(
        token: String,
        id: String,
    ): Flow<BaseResponse<PengajuanResponse>>

    suspend fun getByPegawaiId(
        token: String,
        id: String,
    ): Flow<BaseResponse<List<PengajuanResponse>>>

    suspend fun update(
        token: String,
        id: String,
        ajuan: PengajuanRequest
    ): Flow<BaseResponse<PengajuanResponse>>
}
