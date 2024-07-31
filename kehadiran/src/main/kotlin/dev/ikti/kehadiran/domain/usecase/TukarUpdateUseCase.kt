package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.TukarRequest
import dev.ikti.kehadiran.data.model.TukarResponse
import dev.ikti.kehadiran.domain.repository.TukarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TukarUpdateUseCase @Inject constructor(
    private val tukarRepository: TukarRepository
) {
    suspend fun execute(
        token: String,
        id: String,
        tukar: TukarRequest
    ): Flow<BaseResponse<TukarResponse>> {
        return try {
            tukarRepository.update(token, id, tukar)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
