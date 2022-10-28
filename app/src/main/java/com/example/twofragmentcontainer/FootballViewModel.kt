package com.example.twofragmentcontainer

import android.app.Application
import android.util.JsonReader
import android.util.Log
import androidx.lifecycle.*
import com.example.twofragmentcontainer.data.entities.DatabaseLeague
import com.example.twofragmentcontainer.data.entities.DatabasePlayer
import com.example.twofragmentcontainer.data.getDatabase
import com.example.twofragmentcontainer.network.FootballApi
import com.example.twofragmentcontainer.network.FootballJsonReader
import com.example.twofragmentcontainer.network.retrofit
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.InputStreamReader
import java.io.StringReader
import kotlin.Exception

class FootballViewModel(application: Application): ViewModel() {

    private val repository = Repository(getDatabase(application))

    val leagues = repository.leagues
    var teams = repository.teams
    var players = repository.players

    private var _leagueCode: String? = null
    val leagueCode: String?
        get() = _leagueCode

    private var _teamId: MutableLiveData<Int> = MutableLiveData(-1)
    val teamId: LiveData<Int>
        get() = _teamId

    private var _pickedPlayer: MutableLiveData<DatabasePlayer?> = MutableLiveData(null)
    val pickedPlayer: LiveData<DatabasePlayer?>
        get() = _pickedPlayer

    fun refreshTeams(code: String) {
        viewModelScope.launch {
            try {
                repository.refreshTeams(code)
            } catch (e: Exception) {

            }
        }
    }

    fun refreshPlayers(code: String, teamId: Int) {
        viewModelScope.launch {
            try {
                repository.refreshPlayers(code, teamId)
            } catch(e: Exception) {

            }
        }
    }

    fun changeLeague(leagueCode: String) {
        this._leagueCode = leagueCode
        repository.changeLeague(leagueCode)
        teams = repository.teams
    }

    fun changeTeam(teamId: Int) {
        this._teamId.value = teamId
    }

    fun setPlayersLiveData(teamId: Int) {
        repository.changeTeam(teamId)
        players = repository.players //idk czy potrzebne
    }

    fun setPlayer(player: DatabasePlayer?) {
        _pickedPlayer.value = player
    }

    fun getPlayers(teamId: Int): List<DatabasePlayer>? {
        return repository.getPlayers(teamId)
    }

    fun insertLeagues() {
        val ls = mutableListOf<DatabaseLeague>()
        ls.add(DatabaseLeague(2002, "BL1", "Bundesliga", "https://crests.football-data.org/BL1.png"))
        ls.add(DatabaseLeague(2014, "PD", "Primera Division", "https://crests.football-data.org/PD.png"))
        ls.add(DatabaseLeague(2015, "FL1", "Ligue 1", "https://crests.football-data.org/FL1.png"))
        ls.add(DatabaseLeague(2019, "SA", "Serie A", "https://crests.football-data.org/SA.png"))
        ls.add(DatabaseLeague(2021, "PL", "Premier League", "https://crests.football-data.org/PL.png"))
        viewModelScope.launch {
            repository.refreshLeagues(ls)
        }
    }

    init {
//        refreshTeams("")
//        refreshPlayers("", -1)
    }
}

class FootballViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FootballViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FootballViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}