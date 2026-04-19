package cat.arnaugilco.basketball.ui.pantalles

import android.util.Log
import androidx.navigation.toRoute
import cat.arnaugilco.basketball.model.leagues.League
import cat.arnaugilco.basketball.model.leagues.response.toLeague
import cat.arnaugilco.basketball.navegacio.DestLeagues
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.arnaugilco.basketball.dades.xarxa.BasketballDBClient
import cat.arnaugilco.basketball.dades.xarxa.BasketballDBHelperImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PantallaLliguesViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private var _estat = MutableStateFlow(LliguesEstat())
    val estat = _estat.asStateFlow()
    val apiHelper = BasketballDBHelperImpl(BasketballDBClient.servei)

    init {
        val idPais = savedStateHandle.toRoute<DestLeagues>()
        Log.d("DEBUG_LLIGUES", "ViewModel init - countryId rebut: '${idPais.countryId}'")
        obtenLliguesDelPais(idPais.countryId)
    }

    fun obtenLliguesDelPais(idPais: String) {
        Log.d("DEBUG_LLIGUES", "Cridant API amb countryId: '$idPais'")
        viewModelScope.launch {
            apiHelper.getBasketballLeaguesFlow(contryId = idPais)
                .onStart {
                    Log.d("DEBUG_LLIGUES", "Flow iniciat - estaCarregant = true")
                    _estat.update { it.copy(estaCarregant = true) }
                }
                .catch { error ->
                    Log.e("DEBUG_LLIGUES", "ERROR: ${error::class.simpleName} - ${error.message}")
                    Log.e("DEBUG_LLIGUES", "Causa: ${error.cause}")
                    _estat.update {
                        it.copy(
                            estaCarregant = false,
                            esErroni = true,
                            missatge = error.message ?: "Error Desconegut"
                        )
                    }
                }
                .collect { resposta ->
                    val lligues = resposta.response.map { resp -> resp.toLeague() }
                    _estat.update {
                        it.copy(
                            estaCarregant = false,
                            lligues = lligues,
                            esErroni = false,
                            missatge = ""
                        )
                    }
                }
        }
    }

    data class LliguesEstat(
        val estaCarregant: Boolean = false,
        val esErroni: Boolean = false,
        val missatge: String = "",
        val lligues: List<League> = listOf(),
    )
}