package cat.arnaugilco.basketball.model.teams.response


import cat.arnaugilco.basketball.model.teams.Team
import cat.arnaugilco.basketball.model.teams.response.toCountry
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("country")
    val country: CountryResponse,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nationnal")
    val nationnal: Boolean?
)

fun Response.toTeam() = Team(
    country = country.toCountry(),
    id = id,
    logo = logo,
    name = name,
    nationnal = nationnal,
)