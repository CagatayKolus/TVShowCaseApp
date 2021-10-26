package com.cagataykolus.tvshowcaseapp.di.module

import com.cagataykolus.tvshowcaseapp.data.remote.api.TVShowCaseApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class TVShowCaseApiModule {
    @Singleton
    @Provides
    fun provideRetrofitService(): TVShowCaseApiService = Retrofit.Builder()
        .baseUrl(TVShowCaseApiService.TVSHOWCASE_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(TVShowCaseApiService::class.java)
}
