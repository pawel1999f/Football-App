package com.example.twofragmentcontainer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class DatabasePlayer(
    @PrimaryKey val id: Int,
    val name: String?,
    val position: String?,
    val nationality: String?,
    val dateOfBirth: String?,
    val teamId: Int?
)