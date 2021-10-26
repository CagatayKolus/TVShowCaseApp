package com.cagataykolus.tvshowcaseapp.di.module

import com.cagataykolus.tvshowcaseapp.data.repository.DefaultEpisodeRepository
import com.cagataykolus.tvshowcaseapp.data.repository.EpisodeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class EpisodeRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindEpisodeRepository(repository: DefaultEpisodeRepository): EpisodeRepository
}