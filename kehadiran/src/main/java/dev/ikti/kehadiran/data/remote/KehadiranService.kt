package dev.ikti.kehadiran.data.remote

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.data.model.PresensiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface KehadiranService {
    @GET("kehadiran/jadwal/pegawai/{id}")
    suspend fun getJadwalByPegawaiId(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): BaseResponse<List<JadwalResponse>>

    @GET("kehadiran/presensi/pegawai/{id}")
    suspend fun getPresensiByPegawaiId(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): BaseResponse<List<PresensiResponse>>
}
