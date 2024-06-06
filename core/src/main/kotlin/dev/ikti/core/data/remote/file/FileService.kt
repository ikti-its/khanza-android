package dev.ikti.core.data.remote.file

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.core.data.model.file.FileResponse
import okhttp3.MultipartBody
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileService {
    @Multipart
    @POST("file/img")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): BaseResponse<FileResponse>
}
