package dev.ikti.home.data.remote

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.home.data.model.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeService {
    @GET("m/home")
    suspend fun home(
        @Header("Authorization") token: String,
        @Query("tanggal") tanggal: String,
    ): BaseResponse<HomeResponse>
}
