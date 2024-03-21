package dev.ikti.core.data.repository.user

import dev.ikti.core.data.local.dao.LocalUserDao
import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.model.user.UserLocation
import dev.ikti.core.domain.repository.user.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LocalUserRepositoryImpl @Inject constructor(
    private val localUserDao: LocalUserDao
) : LocalUserRepository {
    override suspend fun getLocalUser(nip: String): Flow<LocalUserEntity> {
        return flowOf(localUserDao.getLocalUser(nip))
    }

    override suspend fun getLocalUserLocation(nip: String): Flow<UserLocation> {
        return flowOf(localUserDao.getLocalUserLocation(nip))
    }

    override suspend fun getLocalUserPhone(nip: String): Flow<String> {
        return flowOf(localUserDao.getLocalUserPhone(nip))
    }

    override suspend fun getLocalUserPhoto(nip: String): Flow<String> {
        return flowOf(localUserDao.getLocalUserPhoto(nip))
    }

    override suspend fun insert(data: LocalUserEntity) {
        return localUserDao.insert(data)
    }

    override suspend fun update(data: LocalUserEntity) {
        return localUserDao.update(data)
    }

    override suspend fun delete(data: LocalUserEntity) {
        return localUserDao.delete(data)
    }

}
