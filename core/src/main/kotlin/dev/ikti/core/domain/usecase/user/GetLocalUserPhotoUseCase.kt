package dev.ikti.core.domain.usecase.user

import dev.ikti.core.domain.repository.user.LocalUserRepository
import dev.ikti.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserPhotoUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<String, Flow<String>> {
    override suspend fun execute(params: String): Flow<String> {
        return localUserRepository.getLocalUserPhoto(params)
    }
}
