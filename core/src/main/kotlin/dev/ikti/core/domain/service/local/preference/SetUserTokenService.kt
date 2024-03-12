package dev.ikti.core.domain.service.local.preference

import dev.ikti.core.domain.repository.local.preference.PreferenceRepository
import dev.ikti.core.domain.service.BaseSuspendService
import javax.inject.Inject

class SetUserTokenService @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseSuspendService<String, Unit> {
    override suspend fun execute(params: String) {
        preferenceRepository.setUserToken(params)
    }
}
