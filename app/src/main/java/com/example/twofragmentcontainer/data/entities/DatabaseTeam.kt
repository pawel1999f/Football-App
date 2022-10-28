package com.example.twofragmentcontainer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class DatabaseTeam(
    @PrimaryKey val id: Int,
    val name: String?,
    val crest: String?,
    val position: Int?,
    val points: Int?,
    val leagueCode: String?
)