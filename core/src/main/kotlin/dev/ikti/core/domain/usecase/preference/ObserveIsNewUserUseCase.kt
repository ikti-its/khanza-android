package dev.ikti.core.domain.usecase.preference

import dev.ikti.core.domain.repository.preference.PreferenceRepository
import dev.ikti.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveIsNewUserUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseUseCase<Unit, Flow<Boolean>> {
    override fun execute(params: Unit): Flow<Boolean> {
        return preferenceRepository.isNewUser()
    }
}
