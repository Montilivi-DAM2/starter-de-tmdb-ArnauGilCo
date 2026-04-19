package cat.arnaugilco.basketball.model.players.response


import cat.arnaugilco.basketball.model.players.Player
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("age")
    val age: Int?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: String?,
    @SerializedName("position")
    val position: String?
)

fun Response.toPlayer() = Player(
    age = age,
    country = country,
    id = id,
    name = name,
    number = number,
    position = position,
)