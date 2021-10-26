package com.cagataykolus.tvshowcaseapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cagataykolus.tvshowcaseapp.model.Episode
import kotlinx.coroutines.flow.Flow

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
/**
 * Data Access Object (DAO) for [com.cagataykolus.tvshowcaseapp.data.local.EpisodeDatabase]
 */
@Dao
interface EpisodeDao {
    /**
     * Inserts [episodes] into the [Episode.TABLE_EPISODE] table.
     * Duplicate values are replaced in the table.
     * @param episodes
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEpisodes(episodes: List<Episode>)

    /**
     * Fetches all the data from the [Episode.TABLE_EPISODE] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Episode.TABLE_EPISODE}")
    fun getAllEpisodes(): Flow<List<Episode>>

    /**
     * Deletes all the data from the [Episode.TABLE_EPISODE] table.
     */
    @Query("DELETE FROM ${Episode.TABLE_EPISODE}")
    suspend fun deleteAllEpisodes()
}