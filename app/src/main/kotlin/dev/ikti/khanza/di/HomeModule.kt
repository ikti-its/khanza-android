package dev.ikti.khanza.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.khanza.data.remote.HomeService
import dev.ikti.khanza.data.repository.HomeRepositoryImpl
import dev.ikti.khanza.domain.repository.HomeRepository
import dev.ikti.khanza.domain.usecase.HomeUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(homeService: HomeService): HomeRepository {
        return HomeRepositoryImpl(homeService)
    }

    @Provides
    @Singleton
    fun provideHomeUseCase(homeRepository: HomeRepository): HomeUseCase {
        return HomeUseCase(homeRepository)
    }
}