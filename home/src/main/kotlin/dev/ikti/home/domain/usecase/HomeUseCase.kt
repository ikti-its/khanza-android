package dev.ikti.home.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.home.data.model.HomeResponse
import dev.ikti.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun execute(
        token: String,
        tanggal: String
    ): Flow<BaseResponse<HomeResponse>> {
        return try {
            homeRepository.home(token, tanggal)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}