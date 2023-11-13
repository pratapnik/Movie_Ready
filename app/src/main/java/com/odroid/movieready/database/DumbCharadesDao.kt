package com.odroid.movieready.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface DumbCharadesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuggestion(dumbCharadeSuggestion: DumbCharadeSuggestion)
}