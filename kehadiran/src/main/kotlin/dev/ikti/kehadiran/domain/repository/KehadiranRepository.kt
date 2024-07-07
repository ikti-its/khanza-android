package dev.ikti.kehadiran.domain.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.data.model.PresensiResponse
import kotlinx.coroutines.flow.Flow

interface KehadiranRepository {
    suspend fun getJadwalByPegawaiId(
        token: String,
        id: String,
    ): Flow<BaseResponse<List<JadwalResponse>>>

    suspend fun getPresensiByPegawaiId(
        token: String,
        id: String,
    ): Flow<BaseResponse<List<PresensiResponse>>>
}
