package com.example.twofragmentcontainer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "league")
data class DatabaseLeague(
    @PrimaryKey val id: Int,
    val code: String?,
    val name: String?,
    val emblem: String?
)