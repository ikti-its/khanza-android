package dev.ikti.core.domain.repository.user

import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.domain.model.user.UserLocation
import dev.ikti.core.domain.repository.BaseLocalRepository
import kotlinx.coroutines.flow.Flow

interface LocalUserRepository : BaseLocalRepository<LocalUserEntity> {
    suspend fun getLocalUser(token: String): Flow<LocalUserEntity>
    suspend fun getLocalUserInfo(token: String): Flow<UserInfo>
    suspend fun getLocalUserLocation(token: String): Flow<UserLocation>
    suspend fun getLocalUserPhoto(token: String): Flow<String>
}
