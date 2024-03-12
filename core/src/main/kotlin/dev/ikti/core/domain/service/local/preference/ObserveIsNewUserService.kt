package dev.ikti.core.domain.service.local.preference

import dev.ikti.core.domain.repository.local.preference.PreferenceRepository
import dev.ikti.core.domain.service.BaseService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveIsNewUserService @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseService<String, Flow<Boolean>> {
    override fun execute(params: String): Flow<Boolean> {
        return preferenceRepository.isNewUser()
    }
}
