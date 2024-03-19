package dev.ikti.core.domain.usecase.local.preference

import dev.ikti.core.domain.repository.local.preference.PreferenceRepository
import dev.ikti.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseUseCase<String, Flow<String>> {
    override fun execute(params: String): Flow<String> {
        return preferenceRepository.getUserToken()
    }
}
