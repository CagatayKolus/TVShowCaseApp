package com.cagataykolus.tvshowcaseapp.data.remote.api

import com.cagataykolus.tvshowcaseapp.model.TvShow
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
/**
 * Service to fetch data using endpoint [TVSHOWCASE_API_URL].
 */
interface TVShowCaseApiService {
    @GET("tvshowcase")
    suspend fun getTVShowCase(): Response<TvShow>

    companion object {
        const val TVSHOWCASE_API_URL = "https://demo2132984.mockable.io/"
    }
}