package com.example.twofragmentcontainer

import com.example.twofragmentcontainer.data.FootballDatabase
import com.example.twofragmentcontainer.data.LeagueDao
import com.example.twofragmentcontainer.data.entities.DatabaseLeague
import com.example.twofragmentcontainer.data.entities.DatabasePlayer
import com.example.twofragmentcontainer.network.FootballApi
import com.example.twofragmentcontainer.network.FootballApiService
import com.example.twofragmentcontainer.network.FootballJsonReader
import com.example.twofragmentcontainer.network.convertToDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: FootballDatabase) {
    var leagues = database.leagueDao.getAllLeagues()
    var teams = database.teamDao.getAllTeams("PL")
    var players = database.playerDao.getAllPlayers(64)

    suspend fun refreshTeams(code: String) {
        withContext(Dispatchers.IO) {
            val ts = FootballJsonReader.getTeams(FootballApi.retrofitService.getTeams(code))
            database.teamDao.insertTeams(ts.convertToDatabase(code))
        }
    }

    suspend fun refreshPlayers(code: String, teamId: Int) {
        withContext(Dispatchers.IO) {
            val ps = FootballJsonReader.getPlayers(FootballApi.retrofitService.getPlayers(code), teamId)
            database.playerDao.insertPlayers(ps.convertToDatabase(teamId))
        }
    }

    suspend fun refreshLeagues(ls: List<DatabaseLeague>) {
        withContext(Dispatchers.IO) {
            database.leagueDao.insertLeagues(ls)
        }
    }

    fun changeLeague(leagueCode: String) {
        teams = database.teamDao.getAllTeams(leagueCode)
    }

    fun changeTeam(teamId: Int) {
        println(teamId)
        players = database.playerDao.getAllPlayers(teamId)
        println(players)
    }

    fun getPlayers(teamId: Int): List<DatabasePlayer>? {
        println(teamId)
        return database.playerDao.getAllPlayers(teamId).value
    }
}