package dev.ikti.profile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.profile.data.remote.ProfileService
import dev.ikti.profile.data.repository.ProfileRepositoryImpl
import dev.ikti.profile.domain.repository.ProfileRepository
import dev.ikti.profile.domain.usecase.UpdateProfileUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {
    @Provides
    @Singleton
    fun provideProfileService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(profileService: ProfileService): ProfileRepository {
        return ProfileRepositoryImpl(profileService)
    }

    @Provides
    @Singleton
    fun provideProfileUseCase(profileRepository: ProfileRepository): UpdateProfileUseCase {
        return UpdateProfileUseCase(profileRepository)
    }
}
