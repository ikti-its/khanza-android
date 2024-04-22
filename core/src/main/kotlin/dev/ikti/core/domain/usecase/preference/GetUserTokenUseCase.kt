package dev.ikti.core.domain.usecase.preference

import dev.ikti.core.domain.repository.preference.PreferenceRepository
import dev.ikti.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : BaseUseCase<Unit, Flow<String>> {
    override fun execute(params: Unit): Flow<String> {
        return preferenceRepository.getUserToken()
    }
}
