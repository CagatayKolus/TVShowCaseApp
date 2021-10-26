package com.cagataykolus.tvshowcaseapp.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cagataykolus.tvshowcaseapp.model.Episode

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
/**
 * Each Migration has a start and end versions and Room runs these migrations to bring the
 * database to the latest version. The migration object that can modify the database and
 * to the necessary changes.
 */
object MigrationDatabase {
    const val DB_VERSION = 2

    val MIGRATION_EPISODE: Array<Migration>
        get() = arrayOf(
            migrationEpisode()
        )

    /**
     *  Creates a new migration between version 1 and 2 for [Episode.TABLE_EPISODE] table.
     */
    private fun migrationEpisode(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${Episode.TABLE_EPISODE} ADD COLUMN body TEXT")
        }
    }
}
