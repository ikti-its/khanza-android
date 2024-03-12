package dev.ikti.core.domain.repository.local.user

import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.model.user.UserLocation
import dev.ikti.core.domain.repository.local.BaseLocalRepository
import kotlinx.coroutines.flow.Flow

interface LocalUserRepository : BaseLocalRepository<LocalUserEntity> {
    suspend fun getLocalUser(nip: String): Flow<LocalUserEntity>
    suspend fun getLocalUserLocation(nip: String): Flow<UserLocation>
    suspend fun getLocalUserPhone(nip: String): Flow<String>
    suspend fun getLocalUserPhoto(nip: String): Flow<String>
}
