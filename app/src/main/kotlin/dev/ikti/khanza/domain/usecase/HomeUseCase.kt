package dev.ikti.khanza.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.khanza.data.model.HomeResponse
import dev.ikti.khanza.domain.repository.HomeRepository
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