package com.cagataykolus.tvshowcaseapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
class Converter {
    companion object RatingConverter {
        @TypeConverter
        @JvmStatic
        fun toRating(data: String?): List<Rating> {
            val listType = object : TypeToken<List<Rating>>() {}.type
            return Gson().fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromRating(data: List<Rating>): String {
            val gson = Gson()
            return gson.toJson(data)
        }
    }
}