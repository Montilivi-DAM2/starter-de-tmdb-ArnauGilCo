package cat.arnaugilco.basketball.dades.xarxa

import cat.arnaugilco.basketball.model.countries.response.ApiResponseGetCountries
import cat.arnaugilco.basketball.model.leagues.response.ApiResponseGetLeagues
import cat.arnaugilco.basketball.model.players.response.ApiResponseGetPlayers
import cat.arnaugilco.basketball.model.teams.response.ApiResponseGetTeams
import kotlinx.coroutines.flow.Flow

interface BasketballDBHelper {

    fun getBasketballCountriesFlow(
        apiKey: String = BasketballDBClient.API_KEY,
    ): Flow<ApiResponseGetCountries>

    fun getBasketballLeaguesFlow(
        apiKey: String = BasketballDBClient.API_KEY,
        contryId: String
    ): Flow<ApiResponseGetLeagues>

    fun getBasketballTeamsFlow(
        apiKey: String = BasketballDBClient.API_KEY,
        leagueId: Int
    ): Flow<ApiResponseGetTeams>

    fun getBasketballPlayersFlow(
        apiKey: String = BasketballDBClient.API_KEY,
        teamId: Int
    ): Flow<ApiResponseGetPlayers>
}