package dev.ikti.core.domain.service.local.user

import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.repository.local.user.LocalUserRepository
import dev.ikti.core.domain.service.BaseSuspendService
import javax.inject.Inject

class UpdateLocalUserService @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendService<LocalUserEntity, Unit> {
    override suspend fun execute(params: LocalUserEntity) {
        return localUserRepository.update(params)
    }
}
