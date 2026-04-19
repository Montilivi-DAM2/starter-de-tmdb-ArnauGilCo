package cat.arnaugilco.basketball.model.leagues.response

import com.google.gson.annotations.SerializedName

data class ApiResponseGetLeagues(
    @SerializedName("errors")
    val errors: Any,           // pot ser [] o {"field": "msg"}
    @SerializedName("get")
    val `get`: String,
    @SerializedName("parameters")
    val parameters: Any,       // pot ser [] o {"code": "ES", ...}
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("results")
    val results: Int
)