package com.example.twofragmentcontainer.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.twofragmentcontainer.data.entities.DatabasePlayer
import com.example.twofragmentcontainer.network.Player

@Dao
interface PlayerDao {
    @Query("select * from player where teamId = :teamId")
    fun getAllPlayers(teamId: Int): LiveData<List<DatabasePlayer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayers(players: List<DatabasePlayer>)
}