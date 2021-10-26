package com.cagataykolus.tvshowcaseapp.data.repository

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Failed<T>(val message: String) : Resource<T>()
}
