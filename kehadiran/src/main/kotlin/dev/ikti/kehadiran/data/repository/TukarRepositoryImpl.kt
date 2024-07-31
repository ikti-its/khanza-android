package dev.ikti.kehadiran.data.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.core.util.NetworkException
import dev.ikti.kehadiran.data.model.TukarRequest
import dev.ikti.kehadiran.data.model.TukarResponse
import dev.ikti.kehadiran.data.remote.TukarService
import dev.ikti.kehadiran.domain.repository.TukarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TukarRepositoryImpl @Inject constructor(
    private val tukarService: TukarService
) : TukarRepository {
    override suspend fun create(
        token: String,
        tukar: TukarRequest
    ): Flow<BaseResponse<TukarResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(tukarService.create(bearer, tukar))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownHostException
                }
            }
        }
    }

    override suspend fun getSender(
        token: String,
        id: String
    ): Flow<BaseResponse<List<TukarResponse>>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(tukarService.getSender(bearer, id))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownHostException
                }
            }
        }
    }

    override suspend fun getRecipient(
        token: String,
        id: String
    ): Flow<BaseResponse<List<TukarResponse>>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(tukarService.getRecipient(bearer, id))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownHostException
                }
            }
        }
    }

    override suspend fun update(
        token: String,
        id: String,
        tukar: TukarRequest
    ): Flow<BaseResponse<TukarResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(tukarService.update(bearer, id, tukar))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownHostException
                }
            }
        }
    }

}
