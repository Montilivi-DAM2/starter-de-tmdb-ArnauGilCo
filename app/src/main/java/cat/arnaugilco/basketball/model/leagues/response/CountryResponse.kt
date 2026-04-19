package cat.arnaugilco.basketball.model.leagues.response


import cat.arnaugilco.basketball.model.countries.Country
import cat.arnaugilco.basketball.model.countries.response.Response
import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

fun CountryResponse.toCountry() = Country(
    code = code,
    flag = flag,
    id = id,
    name = name
)