package cat.arnaugilco.basketball.dades.xarxa

import cat.arnaugilco.basketball.model.countries.response.ApiResponseGetCountries
import cat.arnaugilco.basketball.model.leagues.response.ApiResponseGetLeagues
import cat.arnaugilco.basketball.model.players.response.ApiResponseGetPlayers
import cat.arnaugilco.basketball.model.teams.response.ApiResponseGetTeams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class BasketballDBHelperImpl (val apiService: BasketballDBService): BasketballDBHelper{

    /*
    override fun getBasketballCountriesFlow(
        apiKey: String
    ): Flow<ApiResponseGetCountries> = flow {
        emit(apiService.getBasketballCountries(apiKey))
    }

    override fun getBasketballLeaguesFlow(
        apiKey: String,
        contryId: String
    ): Flow<ApiResponseGetLeagues> = flow {
        emit(apiService.getBasketballLeague(contryId, apiKey))
    }

    override fun getBasketballTeamsFlow(
        apiKey: String,
        leagueId: Int
    ): Flow<ApiResponseGetTeams> = flow {
        emit(apiService.getBasketballTeam(leagueId, apiKey))
    }

    override fun getBasketballPlayersFlow(
        apiKey: String,
        teamId: Int
    ): Flow<ApiResponseGetPlayers> = flow {
        emit(apiService.getBasketballPlayer(teamId, apiKey))
    }
    */

    override fun getBasketballCountriesFlow(
        apiKey: String
    ): Flow<ApiResponseGetCountries> = flow {
        val result = withContext(Dispatchers.IO) {
            apiService.getBasketballCountries(apiKey)
        }
        emit(result)
    }

    override fun getBasketballLeaguesFlow(
        apiKey: String,
        contryId: String
    ): Flow<ApiResponseGetLeagues> = flow {
        val result = withContext(Dispatchers.IO) {
            apiService.getBasketballLeague(contryId, apiKey = apiKey)
        }
        emit(result)
    }

    override fun getBasketballTeamsFlow(
        apiKey: String,
        leagueId: Int
    ): Flow<ApiResponseGetTeams> = flow {
        val result = withContext(Dispatchers.IO) {
            apiService.getBasketballTeam(leagueId, apiKey = apiKey)
        }
        emit(result)
    }

    override fun getBasketballPlayersFlow(
        apiKey: String,
        teamId: Int
    ): Flow<ApiResponseGetPlayers> = flow {
        val result = withContext(Dispatchers.IO) {
            apiService.getBasketballPlayer(teamId, apiKey = apiKey)
        }
        emit(result)
    }

}