package dev.ikti.kehadiran.data.remote

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.TukarRequest
import dev.ikti.kehadiran.data.model.TukarResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TukarService {
    @POST("m/tukar")
    suspend fun create(
        @Header("Authorization") token: String,
        @Body tukar: TukarRequest
    ): BaseResponse<TukarResponse>

    @GET("m/tukar/sender/{id}")
    suspend fun getSender(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): BaseResponse<List<TukarResponse>>

    @GET("m/tukar/recipient/{id}")
    suspend fun getRecipient(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): BaseResponse<List<TukarResponse>>

    @PUT("m/tukar/{id}")
    suspend fun update(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body tukar: TukarRequest
    ): BaseResponse<TukarResponse>
}
