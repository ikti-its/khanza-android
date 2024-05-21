package dev.ikti.home.data.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.home.data.model.HomeResponse
import dev.ikti.home.data.remote.HomeService
import dev.ikti.home.domain.repository.HomeRepository
import dev.ikti.home.util.HomeException
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
        tanggal: String
    ): Flow<BaseResponse<HomeResponse>> {
        val bearer = "Bearer $token"
        return flow {
            try {
                emit(homeService.home(bearer, tanggal))
            } catch (e: HttpException) {
                when (e.response()?.code()) {
                    401 -> throw HomeException.AccountUnauthorizedException
                    404 -> throw HomeException.AccountNotFoundException
                }
            }
        }
    }

}
