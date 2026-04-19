package cat.arnaugilco.basketball.model.players.response


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("season")
    val season: String,
    @SerializedName("team")
    val team: String
)