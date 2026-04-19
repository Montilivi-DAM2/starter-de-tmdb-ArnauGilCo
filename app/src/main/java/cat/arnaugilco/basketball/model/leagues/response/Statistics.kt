package cat.arnaugilco.basketball.model.leagues.response


import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("players")
    val players: Boolean,
    @SerializedName("teams")
    val teams: Boolean
)