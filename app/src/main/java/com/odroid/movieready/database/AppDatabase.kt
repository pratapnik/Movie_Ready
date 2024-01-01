package com.odroid.movieready.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TmdbMovie::class, DumbCharadeSuggestion::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun watchlistDao(): WatchlistDao
    abstract fun dumbCharadesDao(): DumbCharadesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(applicationContext: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java,
                    "app_database")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}