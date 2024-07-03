package dev.ikti.kehadiran.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.kehadiran.data.remote.PresensiService
import dev.ikti.kehadiran.data.repository.PresensiRepositoryImpl
import dev.ikti.kehadiran.domain.repository.PresensiRepository
import dev.ikti.kehadiran.domain.usecase.PresensiAttendUseCase
import dev.ikti.kehadiran.domain.usecase.PresensiGetFotoUseCase
import dev.ikti.kehadiran.domain.usecase.PresensiGetJadwalUseCase
import dev.ikti.kehadiran.domain.usecase.PresensiGetLokasiUseCase
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
    fun providePresensiGetPresensiUseCase(presensiRepository: PresensiRepository): PresensiGetPresensiUseCase {
        return PresensiGetPresensiUseCase(presensiRepository)
    }

    @Provides
    @Singleton
    fun providePresensiAttendUseCase(presensiRepository: PresensiRepository): PresensiAttendUseCase {
        return PresensiAttendUseCase(presensiRepository)
    }

    @Provides
    @Singleton
    fun providePresensiLeaveUseCase(presensiRepository: PresensiRepository): PresensiLeaveUseCase {
        return PresensiLeaveUseCase(presensiRepository)
    }

    @Provides
    @Singleton
    fun providePresensiGetFotoUseCase(presensiRepository: PresensiRepository): PresensiGetFotoUseCase {
        return PresensiGetFotoUseCase(presensiRepository)
    }

    @Provides
    @Singleton
    fun providePresensiGetLokasiUseCase(presensiRepository: PresensiRepository): PresensiGetLokasiUseCase {
        return PresensiGetLokasiUseCase(presensiRepository)
    }
}
