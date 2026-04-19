package cat.arnaugilco.basketball.model.leagues.response


import com.google.gson.annotations.SerializedName

data class Coverage(
    @SerializedName("games")
    val games: Games,
    @SerializedName("odds")
    val odds: Boolean,
    @SerializedName("players")
    val players: Boolean,
    @SerializedName("standings")
    val standings: Boolean
)