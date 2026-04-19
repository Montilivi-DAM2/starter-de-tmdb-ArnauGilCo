package cat.arnaugilco.basketball.model.leagues

import cat.arnaugilco.basketball.model.countries.Country
import cat.arnaugilco.basketball.model.leagues.response.SeasonResponse

data class League (
    val country: Country,
    val logo: String,
    val id: Int,
    val name: String,
    val seasons: List<Season>,
    val type: String
)