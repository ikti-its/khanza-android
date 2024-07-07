package dev.ikti.pegawai.domain.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.pegawai.data.model.KetersediaanResponse
import dev.ikti.pegawai.data.model.OrganisasiResponse
import dev.ikti.pegawai.data.model.PegawaiResponse
import kotlinx.coroutines.flow.Flow

interface PegawaiRepository {
    suspend fun get(token: String): Flow<BaseResponse<PegawaiResponse>>
    suspend fun getKetersediaan(
        token: String,
        tanggal: String
    ): Flow<BaseResponse<List<KetersediaanResponse>>>

    suspend fun getLokasi(
        token: String
    ): Flow<BaseResponse<OrganisasiResponse>>
}
