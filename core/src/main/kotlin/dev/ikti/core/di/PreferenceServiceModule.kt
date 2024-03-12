package dev.ikti.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.core.domain.repository.local.preference.PreferenceRepository
import dev.ikti.core.domain.service.local.preference.ClearUserTokenService
import dev.ikti.core.domain.service.local.preference.GetUserTokenService
import dev.ikti.core.domain.service.local.preference.ObserveIsNewUserService
import dev.ikti.core.domain.service.local.preference.SetNewUserService
import dev.ikti.core.domain.service.local.preference.SetUserTokenService

@Module
@InstallIn(SingletonComponent::class)
object PreferenceServiceModule {
    @Provides
    fun provideObserveIsNewUserService(preferenceRepository: PreferenceRepository): ObserveIsNewUserService {
        return ObserveIsNewUserService(preferenceRepository)
    }

    @Provides
    fun provideSetNewUserService(preferenceRepository: PreferenceRepository): SetNewUserService {
        return SetNewUserService(preferenceRepository)
    }

    @Provides
    fun provideGetUserTokenService(preferenceRepository: PreferenceRepository): GetUserTokenService {
        return GetUserTokenService(preferenceRepository)
    }

    @Provides
    fun provideSetUserTokenService(preferenceRepository: PreferenceRepository): SetUserTokenService {
        return SetUserTokenService(preferenceRepository)
    }

    @Provides
    fun provideClearUserTokenService(preferenceRepository: PreferenceRepository): ClearUserTokenService {
        return ClearUserTokenService(preferenceRepository)
    }
}
