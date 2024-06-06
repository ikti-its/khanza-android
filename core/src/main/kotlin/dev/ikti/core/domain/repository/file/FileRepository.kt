package dev.ikti.core.domain.repository.file

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.core.data.model.file.FileResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface FileRepository {
    suspend fun uploadImage(
        token: String,
        file: MultipartBody.Part
    ): Flow<BaseResponse<FileResponse>>
}
