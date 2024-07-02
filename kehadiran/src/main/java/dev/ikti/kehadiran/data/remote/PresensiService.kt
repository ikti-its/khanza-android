package dev.ikti.kehadiran.data.remote

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.AttendRequest
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.data.model.LeaveRequest
import dev.ikti.kehadiran.data.model.PresensiResponse
import dev.ikti.kehadiran.data.model.StatusPresensiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PresensiService {
    @GET("m/kehadiran/jadwal/{id}")
    suspend fun getJadwal(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Query("hari") hari: Int
    ): BaseResponse<JadwalResponse>

    @GET("m/kehadiran/{id}")
    suspend fun getPresensi(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): BaseResponse<StatusPresensiResponse>

    @POST("kehadiran/presensi/attend")
    suspend fun attend(
        @Header("Authorization") token: String,
        @Body attend: AttendRequest
    ): BaseResponse<PresensiResponse>

    @POST("kehadiran/presensi/leave")
    suspend fun leave(
        @Header("Authorization") token: String,
        @Query("emergency") emergency: Boolean,
        @Body leave: LeaveRequest
    ): BaseResponse<PresensiResponse>


}
