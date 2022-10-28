package com.example.twofragmentcontainer.network

import com.example.twofragmentcontainer.data.entities.DatabaseTeam
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Team(
    val id: Int,
    val name: String?,
    val crest: String?,
    val position: Int?,
    val points: Int?
)

fun (List<Team>).convertToDatabase(leagueCode: String): List<DatabaseTeam> {
    val databaseTeams = mutableListOf<DatabaseTeam>()
    for(t in this) {
        databaseTeams.add(
            DatabaseTeam(
            id = t.id,
            name = t.name,
            position = t.position,
            crest = t.crest,
            points = t.points,
            leagueCode = leagueCode
        )
        )
    }
    return databaseTeams
}