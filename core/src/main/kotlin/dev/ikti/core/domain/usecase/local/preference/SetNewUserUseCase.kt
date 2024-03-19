package dev.ikti.core.domain.usecase.local.preference

import dev.ikti.core.domain.repository.local.preference.PreferenceRepository
import dev.ikti.core.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class SetNewUserUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseSuspendUseCase<Boolean, Unit> {
    override suspend fun execute(params: Boolean) {
        preferenceRepository.setNewUser(params)
    }
}
