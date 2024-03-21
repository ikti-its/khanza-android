package dev.ikti.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.core.domain.repository.preference.PreferenceRepository
import dev.ikti.core.domain.usecase.preference.ClearUserTokenUseCase
import dev.ikti.core.domain.usecase.preference.GetUserTokenUseCase
import dev.ikti.core.domain.usecase.preference.ObserveIsNewUserUseCase
import dev.ikti.core.domain.usecase.preference.SetNewUserUseCase
import dev.ikti.core.domain.usecase.preference.SetUserTokenUseCase

@Module
@InstallIn(SingletonComponent::class)
object PreferenceUseCaseModule {
    @Provides
    fun provideObserveIsNewUserUseCase(preferenceRepository: PreferenceRepository): ObserveIsNewUserUseCase {
        return ObserveIsNewUserUseCase(preferenceRepository)
    }

    @Provides
    fun provideSetNewUserUseCase(preferenceRepository: PreferenceRepository): SetNewUserUseCase {
        return SetNewUserUseCase(preferenceRepository)
    }

    @Provides
    fun provideGetUserTokenUseCase(preferenceRepository: PreferenceRepository): GetUserTokenUseCase {
        return GetUserTokenUseCase(preferenceRepository)
    }

    @Provides
    fun provideSetUserTokenUseCase(preferenceRepository: PreferenceRepository): SetUserTokenUseCase {
        return SetUserTokenUseCase(preferenceRepository)
    }

    @Provides
    fun provideClearUserTokenUseCase(preferenceRepository: PreferenceRepository): ClearUserTokenUseCase {
        return ClearUserTokenUseCase(preferenceRepository)
    }
}
