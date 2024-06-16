package dev.ikti.pegawai.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.pegawai.data.remote.PegawaiService
import dev.ikti.pegawai.data.repository.PegawaiRepositoryImpl
import dev.ikti.pegawai.domain.repository.PegawaiRepository
import dev.ikti.pegawai.domain.usecase.GetKetersediaanUseCase
import dev.ikti.pegawai.domain.usecase.GetPegawaiUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PegawaiModule {
    @Provides
    @Singleton
    fun providePegawaiService(retrofit: Retrofit): PegawaiService {
        return retrofit.create(PegawaiService::class.java)
    }

    @Provides
    @Singleton
    fun providePegawaiRepository(profileService: PegawaiService): PegawaiRepository {
        return PegawaiRepositoryImpl(profileService)
    }

    @Provides
    @Singleton
    fun providePegawaiUseCase(pegawaiRepository: PegawaiRepository): GetPegawaiUseCase {
        return GetPegawaiUseCase(pegawaiRepository)
    }

    @Provides
    @Singleton
    fun provideKetersediaanUseCase(pegawaiRepository: PegawaiRepository): GetKetersediaanUseCase {
        return GetKetersediaanUseCase(pegawaiRepository)
    }
}
