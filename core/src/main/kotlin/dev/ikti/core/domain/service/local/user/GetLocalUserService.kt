package dev.ikti.core.domain.service.local.user

import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.repository.local.user.LocalUserRepository
import dev.ikti.core.domain.service.BaseSuspendService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserService @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendService<String, Flow<LocalUserEntity>> {
    override suspend fun execute(params: String): Flow<LocalUserEntity> {
        return localUserRepository.getLocalUser(params)
    }
}
