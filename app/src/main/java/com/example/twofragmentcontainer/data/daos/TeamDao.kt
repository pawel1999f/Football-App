package com.example.twofragmentcontainer.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.twofragmentcontainer.data.entities.DatabasePlayer
import com.example.twofragmentcontainer.data.entities.DatabaseTeam

@Dao
interface TeamDao {
    @Query("select * from team where leagueCode=:leagueCode order by position asc")
    fun getAllTeams(leagueCode: String): LiveData<List<DatabaseTeam>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(players: List<DatabaseTeam>)
}