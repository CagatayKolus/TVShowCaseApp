package com.cagataykolus.tvshowcaseapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cagataykolus.tvshowcaseapp.data.local.dao.EpisodeDao
import com.cagataykolus.tvshowcaseapp.model.Episode

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
/**
 * EpisodeDatabase provides DAO [EpisodeDao] by using method [getEpisodeDao].
 */
@Database(
    entities = [Episode::class],
    version = MigrationDatabase.DB_VERSION
)
abstract class EpisodeDatabase : RoomDatabase() {

    abstract fun getEpisodeDao(): EpisodeDao

    companion object {
        private const val DB_NAME = "database_episode"

        @Volatile
        private var INSTANCE: EpisodeDatabase? = null

        fun getInstance(context: Context): EpisodeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EpisodeDatabase::class.java,
                    DB_NAME
                ).addMigrations(*MigrationDatabase.MIGRATION_EPISODE).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
