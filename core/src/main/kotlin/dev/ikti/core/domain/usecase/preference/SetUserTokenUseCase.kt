package dev.ikti.core.domain.usecase.preference

import dev.ikti.core.domain.repository.preference.PreferenceRepository
import dev.ikti.core.domain.usecase.BaseSuspendUseCase
import javax.inject.Inject

class SetUserTokenUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseSuspendUseCase<String, Unit> {
    override suspend fun execute(params: String) {
        preferenceRepository.setUserToken(params)
    }
}
