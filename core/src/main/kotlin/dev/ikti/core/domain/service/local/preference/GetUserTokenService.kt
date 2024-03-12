package dev.ikti.core.domain.service.local.preference

import dev.ikti.core.domain.repository.local.preference.PreferenceRepository
import dev.ikti.core.domain.service.BaseService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserTokenService @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseService<String, Flow<String>> {
    override fun execute(params: String): Flow<String> {
        return preferenceRepository.getUserToken()
    }
}
