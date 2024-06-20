package dev.ikti.kehadiran.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.kehadiran.data.remote.KehadiranService
import dev.ikti.kehadiran.data.repository.KehadiranRepositoryImpl
import dev.ikti.kehadiran.domain.repository.KehadiranRepository
import dev.ikti.kehadiran.domain.usecase.KehadiranGetJadwalByPegawaiIdUseCase
import dev.ikti.kehadiran.domain.usecase.KehadiranGetPresensiByPegawaiIdUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KehadiranModule {
    @Provides
    @Singleton
    fun provideKehadiranService(retrofit: Retrofit): KehadiranService {
        return retrofit.create(KehadiranService::class.java)
    }

    @Provides
    @Singleton
    fun provideKehadiranRepository(kehadiranService: KehadiranService): KehadiranRepository {
        return KehadiranRepositoryImpl(kehadiranService)
    }

    @Provides
    @Singleton
    fun provideKehadiranGetJadwalByPegawaiIdUseCase(kehadiranRepository: KehadiranRepository): KehadiranGetJadwalByPegawaiIdUseCase {
        return KehadiranGetJadwalByPegawaiIdUseCase(kehadiranRepository)
    }

    @Provides
    @Singleton
    fun provideKehadiranGetPresensiByPegawaiIdUseCase(kehadiranRepository: KehadiranRepository): KehadiranGetPresensiByPegawaiIdUseCase {
        return KehadiranGetPresensiByPegawaiIdUseCase(kehadiranRepository)
    }
}
