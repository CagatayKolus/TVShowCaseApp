package com.cagataykolus.tvshowcaseapp.data.repository

import com.cagataykolus.tvshowcaseapp.data.local.dao.EpisodeDao
import com.cagataykolus.tvshowcaseapp.data.remote.api.TVShowCaseApiService
import com.cagataykolus.tvshowcaseapp.model.Episode
import com.cagataykolus.tvshowcaseapp.model.TvShow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
interface EpisodeRepository {
    fun getAllEpisodes(
    ): Flow<Resource<List<Episode>>>

    fun deleteAllEpisodes(
    ): Flow<Resource<List<Episode>>>
}

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is single source of data.
 */
@ExperimentalCoroutinesApi
class DefaultEpisodeRepository @Inject constructor(
    private val dao: EpisodeDao,
    private val service: TVShowCaseApiService
) : EpisodeRepository {
    /**
     * Fetched the data from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    override fun getAllEpisodes(
    ): Flow<Resource<List<Episode>>> {
        return object : NetworkBoundRepository<List<Episode>, TvShow>() {

            override suspend fun saveRemoteData(response: TvShow) {
                val episodeList = mutableListOf<Episode>()
                response.seasons.forEach { season ->
                    season.episodes?.forEach { episode ->
                        // Added TV show name to the episode
                        episode.tvShowName = response.title
                        episodeList.add(episode)
                    }
                }
                return dao.addEpisodes(episodeList)
            }

            override fun fetchFromLocal(): Flow<List<Episode>> = dao.getAllEpisodes()

            override suspend fun fetchFromRemote(): Response<TvShow> =
                service.getTVShowCase()

        }.asFlow()
    }

    /**
     * Deletes all data.
     */
    override fun deleteAllEpisodes(): Flow<Resource<List<Episode>>> {
        return object : NetworkBoundRepository<List<Episode>, TvShow>() {

            override suspend fun saveRemoteData(response: TvShow) = dao.deleteAllEpisodes()

            override fun fetchFromLocal(): Flow<List<Episode>> {
                return dao.getAllEpisodes()
            }

            override suspend fun fetchFromRemote(): Response<TvShow> {
                return service.getTVShowCase()
            }

        }.asFlow()
    }
}