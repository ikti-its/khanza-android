package dev.ikti.kehadiran.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.kehadiran.data.remote.PengajuanService
import dev.ikti.kehadiran.data.repository.PengajuanRepositoryImpl
import dev.ikti.kehadiran.domain.repository.PengajuanRepository
import dev.ikti.kehadiran.domain.usecase.PengajuanCreateUseCase
import dev.ikti.kehadiran.domain.usecase.PengajuanGetAllUseCase
import dev.ikti.kehadiran.domain.usecase.PengajuanGetByIdUseCase
import dev.ikti.kehadiran.domain.usecase.PengajuanGetByPegawaiIdUseCase
import dev.ikti.kehadiran.domain.usecase.PengajuanUpdateUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PengajuanModule {
    @Provides
    @Singleton
    fun providePengajuanService(retrofit: Retrofit): PengajuanService {
        return retrofit.create(PengajuanService::class.java)
    }

    @Provides
    @Singleton
    fun providePengajuanRepository(pengajuanService: PengajuanService): PengajuanRepository {
        return PengajuanRepositoryImpl(pengajuanService)
    }

    @Provides
    @Singleton
    fun providePengajuanCreateUseCase(pengajuanRepository: PengajuanRepository): PengajuanCreateUseCase {
        return PengajuanCreateUseCase(pengajuanRepository)
    }

    @Provides
    @Singleton
    fun providePengajuanGetAllUseCase(pengajuanRepository: PengajuanRepository): PengajuanGetAllUseCase {
        return PengajuanGetAllUseCase(pengajuanRepository)
    }

    @Provides
    @Singleton
    fun providePengajuanGetByIdUseCase(pengajuanRepository: PengajuanRepository): PengajuanGetByIdUseCase {
        return PengajuanGetByIdUseCase(pengajuanRepository)
    }

    @Provides
    @Singleton
    fun providePengajuanGetByPegawaiIdUseCase(pengajuanRepository: PengajuanRepository): PengajuanGetByPegawaiIdUseCase {
        return PengajuanGetByPegawaiIdUseCase(pengajuanRepository)
    }

    @Provides
    @Singleton
    fun providePengajuanUpdateUseCase(pengajuanRepository: PengajuanRepository): PengajuanUpdateUseCase {
        return PengajuanUpdateUseCase(pengajuanRepository)
    }
}
