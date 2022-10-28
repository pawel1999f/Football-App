package com.example.twofragmentcontainer.network

import com.example.twofragmentcontainer.data.entities.DatabasePlayer
import com.example.twofragmentcontainer.data.entities.DatabaseTeam

data class Player(
    val id: Int,
    val name: String?,
    val position: String?,
    val nationality: String?,
    val dateOfBirth: String?,
)

fun (List<Player>).convertToDatabase(teamId: Int): List<DatabasePlayer> {
    val databasePlayers = mutableListOf<DatabasePlayer>()
    for(p in this) {
        databasePlayers.add(DatabasePlayer(
            id = p.id,
            name = p.name,
            position = p.position,
            nationality = p.nationality,
            dateOfBirth = p.dateOfBirth,
            teamId = teamId
        ))
    }
    return databasePlayers
}