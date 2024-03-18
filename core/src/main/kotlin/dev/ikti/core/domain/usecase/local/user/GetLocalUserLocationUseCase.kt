package dev.ikti.core.domain.usecase.local.user

import dev.ikti.core.domain.model.user.UserLocation
import dev.ikti.core.domain.repository.local.user.LocalUserRepository
import dev.ikti.core.domain.usecase.BaseSuspendUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserLocationUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<String, Flow<UserLocation>> {
    override suspend fun execute(params: String): Flow<UserLocation> {
        return localUserRepository.getLocalUserLocation(params)
    }
}
