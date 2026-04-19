package cat.arnaugilco.basketball.dades.xarxa

import cat.arnaugilco.basketball.model.countries.response.ApiResponseGetCountries
import cat.arnaugilco.basketball.model.leagues.response.ApiResponseGetLeagues
import cat.arnaugilco.basketball.model.players.response.ApiResponseGetPlayers
import cat.arnaugilco.basketball.model.teams.response.ApiResponseGetTeams
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BasketballDBService {

    @GET("countries")
    suspend fun getBasketballCountries(
        @Header("x-apisports-key") apiKey: String = BasketballDBClient.API_KEY,
    ): ApiResponseGetCountries

    @GET("leagues")
    suspend fun getBasketballLeague(
        @Query("code") countryId: String,
        @Query("season") season: String = "2023-2024",
        @Header("x-apisports-key") apiKey: String = BasketballDBClient.API_KEY,
    ): ApiResponseGetLeagues

    @GET("teams")
    suspend fun getBasketballTeam(
        @Query("league") leagueId: Int,
        @Query("season") season: String = "2023-2024",
        @Header("x-apisports-key") apiKey: String = BasketballDBClient.API_KEY,
    ): ApiResponseGetTeams

    @GET("players")
    suspend fun getBasketballPlayer(
        @Query("team") teamId: Int,
        @Query("season") season: String = "2023-2024",
        @Header("x-apisports-key") apiKey: String = BasketballDBClient.API_KEY,
    ): ApiResponseGetPlayers
}