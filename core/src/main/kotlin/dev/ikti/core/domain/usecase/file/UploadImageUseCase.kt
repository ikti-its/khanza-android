package dev.ikti.core.domain.usecase.file

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.core.data.model.file.FileResponse
import dev.ikti.core.domain.repository.file.FileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val fileRepository: FileRepository
) {
    suspend fun execute(
        token: String,
        file: MultipartBody.Part
    ): Flow<BaseResponse<FileResponse>> {
        return try {
            fileRepository.uploadImage(token, file)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}