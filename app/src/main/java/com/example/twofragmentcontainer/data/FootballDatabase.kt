package com.example.twofragmentcontainer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.twofragmentcontainer.data.daos.PlayerDao
import com.example.twofragmentcontainer.data.daos.TeamDao
import com.example.twofragmentcontainer.data.entities.DatabaseLeague
import com.example.twofragmentcontainer.data.entities.DatabasePlayer
import com.example.twofragmentcontainer.data.entities.DatabaseTeam

@Database(entities = [DatabaseLeague::class, DatabaseTeam::class, DatabasePlayer::class], version = 1, exportSchema = false)
abstract class FootballDatabase: RoomDatabase() {
    abstract val leagueDao: LeagueDao
    abstract val teamDao: TeamDao
    abstract val playerDao: PlayerDao
}

private lateinit var INSTANCE: FootballDatabase

fun getDatabase(context: Context): FootballDatabase {
    synchronized(FootballDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                FootballDatabase::class.java,
                "football").build()
        }
    }
    return INSTANCE
}