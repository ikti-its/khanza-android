package dev.ikti.core.domain.service.local.preference

import dev.ikti.core.domain.repository.local.preference.PreferenceRepository
import dev.ikti.core.domain.service.BaseSuspendService
import javax.inject.Inject

class ClearUserTokenService @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseSuspendService<Unit, Unit> {
    override suspend fun execute(params: Unit) {
        preferenceRepository.clearUserToken()
    }
}
