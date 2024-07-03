package dev.ikti.kehadiran.domain.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.AttendRequest
import dev.ikti.kehadiran.data.model.FotoPegawaiResponse
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.data.model.LeaveRequest
import dev.ikti.kehadiran.data.model.OrganisasiResponse
import dev.ikti.kehadiran.data.model.PresensiResponse
import dev.ikti.kehadiran.data.model.StatusPresensiResponse
import kotlinx.coroutines.flow.Flow

interface PresensiRepository {
    suspend fun getJadwal(
        token: String,
        id: String,
        hari: Int
    ): Flow<BaseResponse<JadwalResponse>>

    suspend fun getPresensi(
        token: String,
        id: String
    ): Flow<BaseResponse<StatusPresensiResponse>>

    suspend fun attend(
        token: String,
        attend: AttendRequest
    ): Flow<BaseResponse<PresensiResponse>>

    suspend fun leave(
        token: String,
        emergency: Boolean,
        leave: LeaveRequest
    ): Flow<BaseResponse<PresensiResponse>>

    suspend fun getFoto(
        token: String,
        id: String
    ): Flow<BaseResponse<FotoPegawaiResponse>>

    suspend fun getLokasi(
        token: String
    ): Flow<BaseResponse<OrganisasiResponse>>
}
