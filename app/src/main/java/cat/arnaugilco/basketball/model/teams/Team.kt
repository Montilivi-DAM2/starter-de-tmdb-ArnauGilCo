package cat.arnaugilco.basketball.model.teams

import cat.arnaugilco.basketball.model.countries.Country

data class Team (
    val country: Country,
    val id: Int,
    val logo: String?,
    val name: String?,
    val nationnal: Boolean?
)