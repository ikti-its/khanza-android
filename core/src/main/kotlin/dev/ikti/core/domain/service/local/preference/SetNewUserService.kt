package dev.ikti.core.domain.service.local.preference

import dev.ikti.core.domain.repository.local.preference.PreferenceRepository
import dev.ikti.core.domain.service.BaseSuspendService
import javax.inject.Inject

class SetNewUserService @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseSuspendService<Boolean, Unit> {
    override suspend fun execute(params: Boolean) {
        preferenceRepository.setNewUser(params)
    }
}
