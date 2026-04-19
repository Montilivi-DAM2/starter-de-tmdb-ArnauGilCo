package cat.arnaugilco.basketball.model.teams.response


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("league")
    val league: String,
    @SerializedName("season")
    val season: String
)