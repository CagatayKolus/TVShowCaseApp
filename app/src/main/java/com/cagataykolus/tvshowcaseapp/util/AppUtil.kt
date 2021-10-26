package com.cagataykolus.tvshowcaseapp.util

import com.cagataykolus.tvshowcaseapp.model.Episode
import java.text.DecimalFormat

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
object AppUtil {
    private const val seasonTag = "S"
    private const val episodeTag = "E"
    private const val connectorTag = " | "

    fun seasonEpisodeTextGenerator(episode: Episode): String {
        val df = DecimalFormat("00")
        val seasonNumber = df.format(episode.Season.toDouble())
        val episodeNumber = df.format(episode.Episode.toDouble())
        return AppUtil.seasonTag.plus(seasonNumber).plus(AppUtil.connectorTag).plus(AppUtil.episodeTag).plus(episodeNumber)
    }
}