package cat.arnaugilco.basketball.model.countries.response

import com.google.gson.annotations.SerializedName

data class ApiResponseGetCountries(
    @SerializedName("errors")
    val errors: Any,
    @SerializedName("get")
    val `get`: String,
    @SerializedName("parameters")
    val parameters: Any,
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("results")
    val results: Int
)