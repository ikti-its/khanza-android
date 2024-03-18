package dev.ikti.core.domain.usecase.local.user

import dev.ikti.core.domain.repository.local.user.LocalUserRepository
import dev.ikti.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserPhoneUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<String, Flow<String>> {
    override suspend fun execute(params: String): Flow<String> {
        return localUserRepository.getLocalUserPhone(params)
    }
}
