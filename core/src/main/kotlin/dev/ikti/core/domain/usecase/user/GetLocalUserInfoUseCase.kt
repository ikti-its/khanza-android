package dev.ikti.core.domain.usecase.user

import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.domain.repository.user.LocalUserRepository
import dev.ikti.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserInfoUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<String, Flow<UserInfo>> {
    override suspend fun execute(params: String): Flow<UserInfo> {
        return localUserRepository.getLocalUserInfo(params)
    }
}
