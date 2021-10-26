package com.cagataykolus.tvshowcaseapp.di.module

import android.app.Application
import com.cagataykolus.tvshowcaseapp.data.local.EpisodeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class TVShowCaseDatabaseModule {
    @Singleton
    @Provides
    fun provideEpisodeDatabase(application: Application) = EpisodeDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideEpisodeDao(database: EpisodeDatabase) = database.getEpisodeDao()
}