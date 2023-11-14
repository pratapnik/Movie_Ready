package com.odroid.movieready.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DumbCharadesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuggestion(dumbCharadeSuggestion: DumbCharadeSuggestion)

    @Query("SELECT * FROM dumb_charades_suggestions ORDER BY time_stamp DESC")
    fun getBollywoodMoviesSuggestions(): Flow<List<DumbCharadeSuggestion>>
}