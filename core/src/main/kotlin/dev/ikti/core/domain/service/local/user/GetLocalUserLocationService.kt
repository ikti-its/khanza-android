package dev.ikti.core.domain.service.local.user

import dev.ikti.core.domain.model.user.UserLocation
import dev.ikti.core.domain.repository.local.user.LocalUserRepository
import dev.ikti.core.domain.service.BaseSuspendService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserLocationService @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendService<String, Flow<UserLocation>> {
    override suspend fun execute(params: String): Flow<UserLocation> {
        return localUserRepository.getLocalUserLocation(params)
    }
}
