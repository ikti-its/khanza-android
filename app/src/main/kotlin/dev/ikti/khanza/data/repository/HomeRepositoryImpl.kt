package dev.ikti.khanza.data.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.khanza.data.model.HomeResponse
import dev.ikti.khanza.data.remote.HomeService
import dev.ikti.khanza.domain.repository.HomeRepository
import dev.ikti.khanza.util.HomeException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRepository {
    override suspend fun home(
        token: String,
        hari: Int,
        tanggal: String
    ): Flow<BaseResponse<HomeResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(homeService.home(bearer, hari, tanggal))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    400 -> throw HomeException.QueryInvalidException
                    401 -> throw HomeException.AccountUnauthorizedException
                    404 -> throw HomeException.AccountNotFoundException
                }
            }
        }
    }

}
