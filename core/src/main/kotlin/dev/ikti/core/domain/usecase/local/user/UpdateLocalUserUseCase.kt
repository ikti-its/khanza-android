package dev.ikti.core.domain.usecase.local.user

import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.repository.local.user.LocalUserRepository
import dev.ikti.core.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class UpdateLocalUserUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<LocalUserEntity, Unit> {
    override suspend fun execute(params: LocalUserEntity) {
        return localUserRepository.update(params)
    }
}
