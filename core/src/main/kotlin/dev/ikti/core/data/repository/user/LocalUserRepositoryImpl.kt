package dev.ikti.core.data.repository.user

import dev.ikti.core.data.local.dao.LocalUserDao
import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.domain.model.user.UserLocation
import dev.ikti.core.domain.repository.user.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LocalUserRepositoryImpl @Inject constructor(
    private val localUserDao: LocalUserDao
) : LocalUserRepository {
    override suspend fun getLocalUser(akun: String): Flow<LocalUserEntity> {
        return flowOf(localUserDao.getLocalUser(akun))
    }

    override suspend fun getLocalUserInfo(akun: String): Flow<UserInfo> {
        return flowOf(localUserDao.getLocalUserInfo(akun))
    }

    override suspend fun getLocalUserLocation(akun: String): Flow<UserLocation> {
        return flowOf(localUserDao.getLocalUserLocation(akun))
    }

    override suspend fun getLocalUserPhoto(akun: String): Flow<String> {
        return flowOf(localUserDao.getLocalUserPhoto(akun))
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
