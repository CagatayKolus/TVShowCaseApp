package com.cagataykolus.tvshowcaseapp.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.cagataykolus.tvshowcaseapp.model.Episode.Companion.TABLE_EPISODE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
@Entity(tableName = TABLE_EPISODE)
@TypeConverters(
    Converter::class
)
@Parcelize
data class Episode(
    @PrimaryKey
    @NonNull
    var imdbID: String,
    var Plot: String,
    var Rated: String,
    var Title: String,
    var Ratings: @RawValue List<Rating>,
    var Writer: String,
    var Actors: String,
    var Type: String,
    var imdbVotes: String,
    var seriesID: String,
    var Episode: String,
    var Director: String,
    var Released: String,
    var Awards: String,
    var Genre: String,
    var imdbRating: String,
    var Poster: String,
    var Season: String,
    var Language: String,
    var Country: String,
    var Runtime: String,
    var Metascore: String,
    var Response: String,
    var Year: String,
    var tvShowName: String? = ""
) : Parcelable {
    companion object {
        const val TABLE_EPISODE = "table_season"
    }
}