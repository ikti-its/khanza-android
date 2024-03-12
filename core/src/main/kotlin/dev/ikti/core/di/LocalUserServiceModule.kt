package dev.ikti.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.core.domain.repository.local.user.LocalUserRepository
import dev.ikti.core.domain.service.local.user.DeleteLocalUserService
import dev.ikti.core.domain.service.local.user.GetLocalUserLocationService
import dev.ikti.core.domain.service.local.user.GetLocalUserPhoneService
import dev.ikti.core.domain.service.local.user.GetLocalUserPhotoService
import dev.ikti.core.domain.service.local.user.GetLocalUserService
import dev.ikti.core.domain.service.local.user.InsertLocalUserService
import dev.ikti.core.domain.service.local.user.UpdateLocalUserService

@Module
@InstallIn(SingletonComponent::class)
object LocalUserServiceModule {
    @Provides
    fun provideGetLocalUserService(localUserRepository: LocalUserRepository): GetLocalUserService {
        return GetLocalUserService(localUserRepository)
    }

    @Provides
    fun provideGetLocalUserLocationService(localUserRepository: LocalUserRepository): GetLocalUserLocationService {
        return GetLocalUserLocationService(localUserRepository)
    }

    @Provides
    fun provideGetLocalUserPhoneService(localUserRepository: LocalUserRepository): GetLocalUserPhoneService {
        return GetLocalUserPhoneService(localUserRepository)
    }

    @Provides
    fun provideGetLocalUserPhotoService(localUserRepository: LocalUserRepository): GetLocalUserPhotoService {
        return GetLocalUserPhotoService(localUserRepository)
    }

    @Provides
    fun provideInsertLocalUserService(localUserRepository: LocalUserRepository): InsertLocalUserService {
        return InsertLocalUserService(localUserRepository)
    }

    @Provides
    fun provideUpdateLocalUserService(localUserRepository: LocalUserRepository): UpdateLocalUserService {
        return UpdateLocalUserService(localUserRepository)
    }

    @Provides
    fun provideDeleteLocalUserService(localUserRepository: LocalUserRepository): DeleteLocalUserService {
        return DeleteLocalUserService(localUserRepository)
    }
}
