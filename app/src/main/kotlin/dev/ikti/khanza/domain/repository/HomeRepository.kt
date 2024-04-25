package dev.ikti.khanza.domain.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.khanza.data.model.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun home(token: String, hari: Int, tanggal: String): Flow<BaseResponse<HomeResponse>>
}
