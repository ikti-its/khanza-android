package dev.ikti.kehadiran.data.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.core.util.NetworkException
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.data.model.PresensiResponse
import dev.ikti.kehadiran.data.remote.KehadiranService
import dev.ikti.kehadiran.domain.repository.KehadiranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KehadiranRepositoryImpl @Inject constructor(
    private val kehadiranService: KehadiranService
) : KehadiranRepository {
    override suspend fun getJadwalByPegawaiId(
        token: String,
        id: String
    ): Flow<BaseResponse<List<JadwalResponse>>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(kehadiranService.getJadwalByPegawaiId(bearer, id))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownException
                }
            }
        }
    }

    override suspend fun getPresensiByPegawaiId(
        token: String,
        id: String
    ): Flow<BaseResponse<List<PresensiResponse>>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(kehadiranService.getPresensiByPegawaiId(bearer, id))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownException
                }
            }
        }
    }
}
