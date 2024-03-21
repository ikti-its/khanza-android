package dev.ikti.core.domain.usecase.preference

import dev.ikti.core.domain.repository.preference.PreferenceRepository
import dev.ikti.core.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class ClearUserTokenUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseSuspendUseCase<Unit, Unit> {
    override suspend fun execute(params: Unit) {
        preferenceRepository.clearUserToken()
    }
}
