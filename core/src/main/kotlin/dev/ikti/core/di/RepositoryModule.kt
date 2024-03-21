package dev.ikti.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.ikti.core.data.local.dao.LocalUserDao
import dev.ikti.core.data.repository.preference.PreferenceRepositoryImpl
import dev.ikti.core.data.repository.user.LocalUserRepositoryImpl
import dev.ikti.core.domain.repository.preference.PreferenceRepository
import dev.ikti.core.domain.repository.user.LocalUserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providePreferenceRepository(
        @ApplicationContext context: Context
    ): PreferenceRepository {
        return PreferenceRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideLocalUserRepository(
        localUserDao: LocalUserDao
    ): LocalUserRepository {
        return LocalUserRepositoryImpl(localUserDao)
    }
}
