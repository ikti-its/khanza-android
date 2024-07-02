package dev.ikti.kehadiran.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.kehadiran.data.remote.PresensiService
import dev.ikti.kehadiran.data.repository.PresensiRepositoryImpl
import dev.ikti.kehadiran.domain.repository.PresensiRepository
import dev.ikti.kehadiran.domain.usecase.PresensiAttendUseCase
import dev.ikti.kehadiran.domain.usecase.PresensiGetJadwalUseCase
import dev.ikti.kehadiran.domain.usecase.PresensiGetPresensiUseCase
import dev.ikti.kehadiran.domain.usecase.PresensiLeaveUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresensiModule {
    @Provides
    @Singleton
    fun providePresensiService(retrofit: Retrofit): PresensiService {
        return retrofit.create(PresensiService::class.java)
    }

    @Provides
    @Singleton
    fun providePresensiRepository(presensiService: PresensiService): PresensiRepository {
        return PresensiRepositoryImpl(presensiService)
    }

    @Provides
    @Singleton
    fun providePresensiGetJadwalUseCase(presensiRepository: PresensiRepository): PresensiGetJadwalUseCase {
        return PresensiGetJadwalUseCase(presensiRepository)
    }

    @Provides
    @Singleton
    fun providePresensiGetPresensiUseCase(kehadiranRepository: PresensiRepository): PresensiGetPresensiUseCase {
        return PresensiGetPresensiUseCase(kehadiranRepository)
    }

    @Provides
    @Singleton
    fun providePresensiAttendUseCase(kehadiranRepository: PresensiRepository): PresensiAttendUseCase {
        return PresensiAttendUseCase(kehadiranRepository)
    }

    @Provides
    @Singleton
    fun providePresensiLeaveUseCase(kehadiranRepository: PresensiRepository): PresensiLeaveUseCase {
        return PresensiLeaveUseCase(kehadiranRepository)
    }
}
