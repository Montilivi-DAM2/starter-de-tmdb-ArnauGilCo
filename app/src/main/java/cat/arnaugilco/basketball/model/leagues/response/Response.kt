package cat.arnaugilco.basketball.model.leagues.response


import cat.arnaugilco.basketball.model.leagues.League
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("country")
    val country: CountryResponse,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("seasons")
    val seasons: List<SeasonResponse>,
    @SerializedName("type")
    val type: String
)

fun Response.toLeague() = League(
    country = country.toCountry(),
    id = id,
    logo = logo,
    name = name,
    seasons = seasons.map { it.toSeason() },
    type = type
)