package dev.ikti.core.data.repository.file

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.core.data.model.file.FileResponse
import dev.ikti.core.data.remote.file.FileService
import dev.ikti.core.domain.repository.file.FileRepository
import dev.ikti.core.util.NetworkException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileRepositoryImpl @Inject constructor(
    private val fileService: FileService
) : FileRepository {
    override suspend fun uploadImage(
        token: String,
        file: MultipartBody.Part
    ): Flow<BaseResponse<FileResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(fileService.uploadImage(bearer, file))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    400 -> throw NetworkException.FileUnsupportedException
                    401 -> throw NetworkException.UnauthorizedException
                    else -> throw NetworkException.UnknownException
                }
            }
        }
    }
}
