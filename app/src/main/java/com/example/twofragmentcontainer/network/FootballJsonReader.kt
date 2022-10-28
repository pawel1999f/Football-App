package com.example.twofragmentcontainer.network

import android.util.JsonReader
import android.util.Log
import java.io.StringReader

class FootballJsonReader {
    companion object {
        fun getTeams(json: String): List<Team> {
            val reader: JsonReader = JsonReader(StringReader(json))

            reader.beginObject();
            while (reader.hasNext()) {
                val name = reader.nextName();
//                println(name)
                if (name.equals("standings")) {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        reader.beginObject()
                        while (reader.hasNext()) {
                            val n = reader.nextName()
                            if (n.equals("table")) {
                                return readTeams(reader)
                            } else {
                                reader.skipValue()
                            }
                        }
                        reader.endObject()
                    }
                    reader.endArray()
                } else {
                    reader.skipValue()
                }
            }
            reader.endObject();

            reader.close()
            return listOf()
        }

        private fun readTeams(reader: JsonReader): List<Team> {
            val teams = mutableListOf<Team>()
            reader.beginArray()
            while (reader.hasNext()) {
                teams.add(readTeam(reader))
            }
            reader.endArray()
            return teams
        }

        private fun readTeam(reader: JsonReader): Team {
            var teamId: Int? = null
            var teamName: String? = null
            var teamCrest: String? = null
            var teamPosition: Int? = null
            var teamPoints: Int? = null


            reader.beginObject()
            while (reader.hasNext()) {
                val name = reader.nextName()
//            println(name)
                if (name.equals("team")) {
                    reader.beginObject()
                    while (reader.hasNext()) {
                        val n = reader.nextName()
//                    println(n)
                        if (n.equals("id")) {
                            teamId = reader.nextInt()
                        } else if (n.equals("name")) {
                            teamName = reader.nextString()
                        } else if (n.equals("crest")) {
                            teamCrest = reader.nextString()
                        } else {
                            reader.skipValue()
                        }
                    }
                    reader.endObject()
                } else if (name.equals("position")) {
                    teamPosition = reader.nextInt()
                } else if (name.equals("points")) {
                    teamPoints = reader.nextInt()
                } else {
                    reader.skipValue()
                }
            }
            reader.endObject()
            return Team(teamId!!, teamName, teamCrest, teamPosition, teamPoints)
        }

        fun getPlayers(json: String, teamId: Int): List<Player> {
            val reader: JsonReader = JsonReader(StringReader(json))

            reader.beginObject();
            while (reader.hasNext()) {
                val name = reader.nextName();
//                println(name)
                if (name.equals("teams")) {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        reader.beginObject()
                        var id: Int? = null
                        while (reader.hasNext()) {
                            val n = reader.nextName()
                            if (n.equals("squad") && teamId == id) {
                                return readSquad(reader)
                            } else if (n.equals("id")) {
                                id = reader.nextInt()
                            } else {
                                reader.skipValue()
                            }
                        }
                        reader.endObject()
                    }
                    reader.endArray()
                } else {
                    reader.skipValue()
                }
            }
            reader.endObject();

            reader.close()
            return listOf()
        }

        private fun readSquad(reader: JsonReader): List<Player> {
            val players = mutableListOf<Player>()
            reader.beginArray()
            while (reader.hasNext()) {
                players.add(readPlayer(reader))
            }
            reader.endArray()
            return players
        }

        private fun readPlayer(reader: JsonReader): Player {

            var id: Int? = null
            var name: String? = null
            var position: String? = null
            var nationality: String? = null
            var dateOfBirth: String? = null
            reader.beginObject()
            while (reader.hasNext()) {
                val n = reader.nextName()
                when (n) {
                    "id" -> id = reader.nextInt()
                    "name" -> name = reader.nextString()
                    "position" -> position = reader.nextString()
                    "nationality" -> nationality = reader.nextString()
                    "dateOfBirth" -> dateOfBirth = reader.nextString()
//                "shirtNumber" -> shirtNumber = reader.nextInt()
//                "marketValue" -> marketValue = reader.nextInt()
                    else -> reader.skipValue()
                }


            }
            reader.endObject()
            val pl = Player(id!!, name, position, nationality, dateOfBirth)
//            println(pl)
            return pl
        }
    }
}