package dev.ikti.core.domain.usecase.local.preference

import dev.ikti.core.domain.repository.local.preference.PreferenceRepository
import dev.ikti.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveIsNewUserUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseUseCase<String, Flow<Boolean>> {
    override fun execute(params: String): Flow<Boolean> {
        return preferenceRepository.isNewUser()
    }
}
