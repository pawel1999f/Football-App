package com.example.twofragmentcontainer.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.twofragmentcontainer.data.entities.DatabaseLeague

@Dao
interface LeagueDao {
    @Query("select * from league")
    fun getAllLeagues(): LiveData<List<DatabaseLeague>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLeagues(leagues: List<DatabaseLeague>)
}