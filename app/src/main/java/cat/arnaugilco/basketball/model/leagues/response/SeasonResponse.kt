package cat.arnaugilco.basketball.model.leagues.response

import cat.arnaugilco.basketball.model.leagues.Season
import com.google.gson.annotations.SerializedName

data class SeasonResponse(
    @SerializedName("coverage")
    val coverage: Coverage,
    @SerializedName("end")
    val end: String,
    @SerializedName("season")
    val season: String,   // "2023-2024", no un Int
    @SerializedName("start")
    val start: String
)

fun SeasonResponse.toSeason() = Season(
    end = end,
    season = season,
    start = start,
)