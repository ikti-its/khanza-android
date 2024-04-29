package dev.ikti.home.domain.repository

import dev.ikti.core.data.model.BaseResponse
import dev.ikti.home.data.model.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun home(token: String, tanggal: String): Flow<BaseResponse<dev.ikti.home.data.model.HomeResponse>>
}
