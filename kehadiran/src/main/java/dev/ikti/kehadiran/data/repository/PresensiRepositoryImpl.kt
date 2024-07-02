package dev.ikti.kehadiran.data.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.core.util.NetworkException
import dev.ikti.kehadiran.data.model.AttendRequest
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.data.model.LeaveRequest
import dev.ikti.kehadiran.data.model.PresensiResponse
import dev.ikti.kehadiran.data.model.StatusPresensiResponse
import dev.ikti.kehadiran.data.remote.PresensiService
import dev.ikti.kehadiran.domain.repository.PresensiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PresensiRepositoryImpl @Inject constructor(
    private val presensiService: PresensiService
) : PresensiRepository {
    override suspend fun getJadwal(
        token: String,
        id: String,
        hari: Int
    ): Flow<BaseResponse<JadwalResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(presensiService.getJadwal(bearer, id, hari))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownException
                }
            }
        }
    }

    override suspend fun getPresensi(
        token: String,
        id: String
    ): Flow<BaseResponse<StatusPresensiResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(presensiService.getPresensi(bearer, id))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownException
                }
            }
        }
    }

    override suspend fun attend(
        token: String,
        attend: AttendRequest
    ): Flow<BaseResponse<PresensiResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(presensiService.attend(bearer, attend))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownException
                }
            }
        }
    }

    override suspend fun leave(
        token: String,
        emergency: Boolean,
        leave: LeaveRequest
    ): Flow<BaseResponse<PresensiResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(presensiService.leave(bearer, emergency, leave))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownException
                }
            }
        }
    }
}
