package cat.arnaugilco.basketball.model.leagues.response


import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("statistics")
    val statistics: Statistics
)