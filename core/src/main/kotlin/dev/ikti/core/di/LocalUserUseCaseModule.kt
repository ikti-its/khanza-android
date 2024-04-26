package dev.ikti.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.core.domain.repository.user.LocalUserRepository
import dev.ikti.core.domain.usecase.user.DeleteLocalUserUseCase
import dev.ikti.core.domain.usecase.user.GetLocalUserLocationUseCase
import dev.ikti.core.domain.usecase.user.GetLocalUserInfoUseCase
import dev.ikti.core.domain.usecase.user.GetLocalUserPhotoUseCase
import dev.ikti.core.domain.usecase.user.GetLocalUserUseCase
import dev.ikti.core.domain.usecase.user.InsertLocalUserUseCase
import dev.ikti.core.domain.usecase.user.UpdateLocalUserUseCase

@Module
@InstallIn(SingletonComponent::class)
object LocalUserUseCaseModule {
    @Provides
    fun provideGetLocalUserUseCase(localUserRepository: LocalUserRepository): GetLocalUserUseCase {
        return GetLocalUserUseCase(localUserRepository)
    }
    
    @Provides
    fun provideGetLocalUserInfoUseCase(localUserRepository: LocalUserRepository): GetLocalUserInfoUseCase {
        return GetLocalUserInfoUseCase(localUserRepository)
    }

    @Provides
    fun provideGetLocalUserLocationUseCase(localUserRepository: LocalUserRepository): GetLocalUserLocationUseCase {
        return GetLocalUserLocationUseCase(localUserRepository)
    }

    @Provides
    fun provideGetLocalUserPhotoUseCase(localUserRepository: LocalUserRepository): GetLocalUserPhotoUseCase {
        return GetLocalUserPhotoUseCase(localUserRepository)
    }

    @Provides
    fun provideInsertLocalUserUseCase(localUserRepository: LocalUserRepository): InsertLocalUserUseCase {
        return InsertLocalUserUseCase(localUserRepository)
    }

    @Provides
    fun provideUpdateLocalUserUseCase(localUserRepository: LocalUserRepository): UpdateLocalUserUseCase {
        return UpdateLocalUserUseCase(localUserRepository)
    }

    @Provides
    fun provideDeleteLocalUserUseCase(localUserRepository: LocalUserRepository): DeleteLocalUserUseCase {
        return DeleteLocalUserUseCase(localUserRepository)
    }
}
