package dev.ikti.core.di.file

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.ikti.core.data.remote.file.FileService
import dev.ikti.core.data.repository.file.FileRepositoryImpl
import dev.ikti.core.domain.repository.file.FileRepository
import dev.ikti.core.domain.usecase.file.UploadImageUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileModule {
    @Provides
    @Singleton
    fun provideFileService(retrofit: Retrofit): FileService {
        return retrofit.create(FileService::class.java)
    }

    @Provides
    @Singleton
    fun provideFileRepository(fileService: FileService): FileRepository {
        return FileRepositoryImpl(fileService)
    }

    @Provides
    @Singleton
    fun provideFileUseCase(fileRepository: FileRepository): UploadImageUseCase {
        return UploadImageUseCase(fileRepository)
    }
}
