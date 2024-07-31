package dev.ikti.kehadiran.domain.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.kehadiran.data.model.TukarRequest
import dev.ikti.kehadiran.data.model.TukarResponse
import kotlinx.coroutines.flow.Flow

interface TukarRepository {
    suspend fun create(
        token: String,
        tukar: TukarRequest
    ): Flow<BaseResponse<TukarResponse>>

    suspend fun getSender(
        token: String,
        id: String
    ): Flow<BaseResponse<List<TukarResponse>>>

    suspend fun getRecipient(
        token: String,
        id: String
    ): Flow<BaseResponse<List<TukarResponse>>>

    suspend fun update(
        token: String,
        id: String,
        tukar: TukarRequest
    ): Flow<BaseResponse<TukarResponse>>
}
