package dev.ikti.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.core.domain.repository.local.user.LocalUserRepository
import dev.ikti.core.domain.usecase.local.user.DeleteLocalUserUseCase
import dev.ikti.core.domain.usecase.local.user.GetLocalUserLocationUseCase
import dev.ikti.core.domain.usecase.local.user.GetLocalUserPhoneUseCase
import dev.ikti.core.domain.usecase.local.user.GetLocalUserPhotoUseCase
import dev.ikti.core.domain.usecase.local.user.GetLocalUserUseCase
import dev.ikti.core.domain.usecase.local.user.InsertLocalUserUseCase
import dev.ikti.core.domain.usecase.local.user.UpdateLocalUserUseCase

@Module
@InstallIn(SingletonComponent::class)
object LocalUserServiceModule {
    @Provides
    fun provideGetLocalUserService(localUserRepository: LocalUserRepository): GetLocalUserUseCase {
        return GetLocalUserUseCase(localUserRepository)
    }

    @Provides
    fun provideGetLocalUserLocationService(localUserRepository: LocalUserRepository): GetLocalUserLocationUseCase {
        return GetLocalUserLocationUseCase(localUserRepository)
    }

    @Provides
    fun provideGetLocalUserPhoneService(localUserRepository: LocalUserRepository): GetLocalUserPhoneUseCase {
        return GetLocalUserPhoneUseCase(localUserRepository)
    }

    @Provides
    fun provideGetLocalUserPhotoService(localUserRepository: LocalUserRepository): GetLocalUserPhotoUseCase {
        return GetLocalUserPhotoUseCase(localUserRepository)
    }

    @Provides
    fun provideInsertLocalUserService(localUserRepository: LocalUserRepository): InsertLocalUserUseCase {
        return InsertLocalUserUseCase(localUserRepository)
    }

    @Provides
    fun provideUpdateLocalUserService(localUserRepository: LocalUserRepository): UpdateLocalUserUseCase {
        return UpdateLocalUserUseCase(localUserRepository)
    }

    @Provides
    fun provideDeleteLocalUserService(localUserRepository: LocalUserRepository): DeleteLocalUserUseCase {
        return DeleteLocalUserUseCase(localUserRepository)
    }
}
