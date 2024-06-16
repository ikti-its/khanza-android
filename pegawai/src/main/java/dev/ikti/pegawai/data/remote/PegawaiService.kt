package dev.ikti.pegawai.data.remote

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.pegawai.data.model.KetersediaanResponse
import dev.ikti.pegawai.data.model.PegawaiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PegawaiService {
    @GET("m/pegawai")
    suspend fun get(
        @Header("Authorization") token: String
    ): BaseResponse<PegawaiResponse>

    @GET("m/ketersediaan")
    suspend fun getKetersediaan(
        @Header("Authorization") token: String,
        @Query("tanggal") tanggal: String
    ): BaseResponse<List<KetersediaanResponse>>
}
