package dev.ikti.core.domain.service.local.user

import dev.ikti.core.domain.repository.local.user.LocalUserRepository
import dev.ikti.core.domain.service.BaseSuspendService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserPhoneService @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendService<String, Flow<String>> {
    override suspend fun execute(params: String): Flow<String> {
        return localUserRepository.getLocalUserPhone(params)
    }
}
