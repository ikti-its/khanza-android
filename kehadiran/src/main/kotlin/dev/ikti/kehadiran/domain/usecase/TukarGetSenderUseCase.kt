package dev.ikti.kehadiran.domain.usecase

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.TukarResponse
import dev.ikti.kehadiran.domain.repository.TukarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TukarGetSenderUseCase @Inject constructor(
    private val tukarRepository: TukarRepository
) {
    suspend fun execute(
        token: String,
        id: String
    ): Flow<BaseResponse<List<TukarResponse>>> {
        return try {
            tukarRepository.getSender(token, id)
        } catch (e: Exception) {
            flow {
                throw e
            }
        }
    }
}
