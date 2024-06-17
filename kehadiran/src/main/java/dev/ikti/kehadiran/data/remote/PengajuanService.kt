package dev.ikti.kehadiran.data.remote

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.PengajuanRequest
import dev.ikti.kehadiran.data.model.PengajuanResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PengajuanService {
    @POST("kehadiran/cuti")
    suspend fun create(
        @Header("Authorization") token: String,
        @Body ajuan: PengajuanRequest
    ): BaseResponse<PengajuanResponse>

    @GET("kehadiran/cuti")
    suspend fun getAll(
        @Header("Authorization") token: String
    ): BaseResponse<List<PengajuanResponse>>

    @GET("kehadiran/cuti/{id}")
    suspend fun getById(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): BaseResponse<PengajuanResponse>

    @GET("kehadiran/cuti/pegawai/{id}")
    suspend fun getByPegawaiId(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): BaseResponse<List<PengajuanResponse>>

    @PUT("kehadiran/cuti/{id}")
    suspend fun update(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body ajuan: PengajuanRequest
    ): BaseResponse<PengajuanResponse>
}
