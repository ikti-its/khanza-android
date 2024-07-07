package dev.ikti.pegawai.data.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.core.util.NetworkException
import dev.ikti.pegawai.data.model.KetersediaanResponse
import dev.ikti.pegawai.data.model.OrganisasiResponse
import dev.ikti.pegawai.data.model.PegawaiResponse
import dev.ikti.pegawai.data.remote.PegawaiService
import dev.ikti.pegawai.domain.repository.PegawaiRepository
import dev.ikti.pegawai.util.PegawaiException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PegawaiRepositoryImpl @Inject constructor(
    private val pegawaiService: PegawaiService
) : PegawaiRepository {
    override suspend fun get(token: String): Flow<BaseResponse<PegawaiResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(pegawaiService.get(bearer))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw PegawaiException.AccountNotFoundException
                }
            }
        }
    }

    override suspend fun getKetersediaan(
        token: String,
        tanggal: String
    ): Flow<BaseResponse<List<KetersediaanResponse>>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(pegawaiService.getKetersediaan(bearer, tanggal))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw PegawaiException.AccountNotFoundException
                }
            }
        }
    }

    override suspend fun getLokasi(
        token: String
    ): Flow<BaseResponse<OrganisasiResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(pegawaiService.getLokasi(bearer))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    404 -> throw NetworkException.NotFoundException
                    else -> throw NetworkException.UnknownHostException
                }
            }
        }
    }
}
