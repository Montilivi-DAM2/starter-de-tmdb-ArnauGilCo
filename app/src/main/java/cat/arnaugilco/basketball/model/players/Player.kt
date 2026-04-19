package cat.arnaugilco.basketball.model.players

import com.google.gson.annotations.SerializedName

data class Player (
    val age: Int?,
    val country: String?,
    val id: Int,
    val name: String?,
    val number: String?,
    val position: String?
)