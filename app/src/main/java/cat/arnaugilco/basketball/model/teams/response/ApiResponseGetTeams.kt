package cat.arnaugilco.basketball.model.teams.response

import com.google.gson.annotations.SerializedName

data class ApiResponseGetTeams(
    @SerializedName("errors")
    val errors: Any,           // pot ser [] o {"field": "msg"}
    @SerializedName("get")
    val `get`: String,
    @SerializedName("parameters")
    val parameters: Any,       // pot ser [] o {"league": "95", ...}
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("results")
    val results: Int
)