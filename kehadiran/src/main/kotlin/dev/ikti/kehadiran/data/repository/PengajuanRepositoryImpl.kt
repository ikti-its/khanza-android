package dev.ikti.kehadiran.data.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.PegawaiResponse
import dev.ikti.kehadiran.data.model.PengajuanRequest
import dev.ikti.kehadiran.data.model.PengajuanResponse
import dev.ikti.kehadiran.data.remote.PengajuanService
import dev.ikti.kehadiran.domain.repository.PengajuanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PengajuanRepositoryImpl @Inject constructor(
    private val pengajuanService: PengajuanService
) : PengajuanRepository {
    override suspend fun create(
        token: String,
        ajuan: PengajuanRequest
    ): Flow<BaseResponse<PengajuanResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(pengajuanService.create(bearer, ajuan))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    // TODO: Implement error handling
                }
            }
        }
    }

    override suspend fun getAll(token: String): Flow<BaseResponse<List<PengajuanResponse>>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(pengajuanService.getAll(bearer))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    // TODO: Implement error handling
                }
            }
        }
    }

    override suspend fun getByPegawaiId(
        token: String,
        id: String
    ): Flow<BaseResponse<List<PengajuanResponse>>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(pengajuanService.getByPegawaiId(bearer, id))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    // TODO: Implement error handling
                }
            }
        }
    }

    override suspend fun update(
        token: String,
        id: String,
        ajuan: PengajuanRequest
    ): Flow<BaseResponse<PengajuanResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(pengajuanService.update(bearer, id, ajuan))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    // TODO: Implement error handling
                }
            }
        }
    }

    override suspend fun getPegawai(
        token: String,
        id: String
    ): Flow<BaseResponse<PegawaiResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(pengajuanService.getPegawai(bearer, id))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    // TODO: Implement error handling
                }
            }
        }
    }
}
