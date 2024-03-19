package dev.ikti.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.core.domain.repository.remote.auth.AuthRepository
import dev.ikti.core.domain.usecase.remote.auth.LoginUseCase

@Module
@InstallIn(SingletonComponent::class)
object AuthUseCaseModule {
    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }
}
