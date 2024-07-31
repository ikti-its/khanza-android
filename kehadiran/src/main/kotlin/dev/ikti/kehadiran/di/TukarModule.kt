package dev.ikti.kehadiran.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.kehadiran.data.remote.TukarService
import dev.ikti.kehadiran.data.repository.TukarRepositoryImpl
import dev.ikti.kehadiran.domain.repository.TukarRepository
import dev.ikti.kehadiran.domain.usecase.TukarCreateUseCase
import dev.ikti.kehadiran.domain.usecase.TukarGetRecipientUseCase
import dev.ikti.kehadiran.domain.usecase.TukarGetSenderUseCase
import dev.ikti.kehadiran.domain.usecase.TukarUpdateUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TukarModule {
    @Provides
    @Singleton
    fun provideTukarService(retrofit: Retrofit): TukarService {
        return retrofit.create(TukarService::class.java)
    }

    @Provides
    @Singleton
    fun provideTukarRepository(tukarService: TukarService): TukarRepository {
        return TukarRepositoryImpl(tukarService)
    }

    @Provides
    @Singleton
    fun provideTukarCreateUseCase(tukarRepository: TukarRepository): TukarCreateUseCase {
        return TukarCreateUseCase(tukarRepository)
    }

    @Provides
    @Singleton
    fun provideTukarGetSenderUseCase(tukarRepository: TukarRepository): TukarGetSenderUseCase {
        return TukarGetSenderUseCase(tukarRepository)
    }

    @Provides
    @Singleton
    fun provideTukarGetRecipientUseCase(tukarRepository: TukarRepository): TukarGetRecipientUseCase {
        return TukarGetRecipientUseCase(tukarRepository)
    }

    @Provides
    @Singleton
    fun provideTukarUpdateUseCase(tukarRepository: TukarRepository): TukarUpdateUseCase {
        return TukarUpdateUseCase(tukarRepository)
    }
}
