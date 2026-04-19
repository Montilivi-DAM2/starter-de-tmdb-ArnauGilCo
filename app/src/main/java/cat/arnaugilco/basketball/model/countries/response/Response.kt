package cat.arnaugilco.basketball.model.countries.response

import cat.arnaugilco.basketball.model.countries.Country
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("code")
    val code: String?,
    @SerializedName("flag")
    val flag: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

fun Response.toCountry() = Country(
    code = code,
    flag = flag,
    id = id,
    name = name
)