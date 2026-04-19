package cat.arnaugilco.basketball.model.players.response

import com.google.gson.annotations.SerializedName

data class ApiResponseGetPlayers(
    @SerializedName("errors")
    val errors: Any,           // pot ser [] o {"field": "msg"}
    @SerializedName("get")
    val `get`: String,
    @SerializedName("parameters")
    val parameters: Any,       // pot ser [] o {"team": "1132", ...}
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("results")
    val results: Int
)