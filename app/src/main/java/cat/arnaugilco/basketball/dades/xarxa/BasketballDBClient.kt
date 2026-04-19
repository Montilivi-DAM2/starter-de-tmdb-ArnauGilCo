package cat.arnaugilco.basketball.dades.xarxa

import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BasketballDBClient {
    const val API_KEY = "52ee02cf43e07105b94bd856cefbe574"
    const val BASE_URL = "https://v1.basketball.api-sports.io/"

    val gsonConverter = GsonBuilder()
        .setStrictness(Strictness.LENIENT)
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gsonConverter))
        .build()

    val servei = retrofit.create(BasketballDBService::class.java)
}