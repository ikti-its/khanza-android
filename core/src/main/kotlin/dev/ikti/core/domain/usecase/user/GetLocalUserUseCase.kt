package dev.ikti.core.domain.usecase.user

import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.repository.user.LocalUserRepository
import dev.ikti.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<String, Flow<LocalUserEntity>> {
    override suspend fun execute(params: String): Flow<LocalUserEntity> {
        return localUserRepository.getLocalUser(params)
    }
}
