package cat.arnaugilco.basketball.ui.pantalles

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.arnaugilco.basketball.dades.xarxa.BasketballDBClient
import cat.arnaugilco.basketball.dades.xarxa.BasketballDBHelperImpl
import cat.arnaugilco.basketball.model.countries.response.toCountry
import cat.arnaugilco.basketball.model.countries.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PantallaSeleccioDePaisViewModel (savedStateHandle: SavedStateHandle): ViewModel() {
    private var _estat = MutableStateFlow(SeleccioPaisEstat())

    val estat = _estat.asStateFlow()

    val apiHelper = BasketballDBHelperImpl(BasketballDBClient.servei)

    init {
        obtenPaisosDisponibles()
    }

    fun obtenPaisosDisponibles() {
        viewModelScope.launch {
            apiHelper.getBasketballCountriesFlow()
                .flowOn(Dispatchers.IO)
                .onStart { _estat.update { it.copy(estaCarregant = true) } }
                .catch { error ->
                    _estat.update {
                        it.copy(
                            estaCarregant = false,
                            esErroni = true,
                            missatge = error.message ?: "Error Desconegut"
                        )
                    }
                }
                .collect {
                    val paisos = it.response
                        .mapNotNull { response ->
                            if (response.code != null) response.toCountry() else null
                        }
                    _estat.update {
                        it.copy(
                            estaCarregant = false,
                            paisos = paisos,
                            esErroni = false,
                            missatge = ""
                        )
                    }
                }
        }
    }

    data class SeleccioPaisEstat(
        val estaCarregant: Boolean = false,
        val esErroni: Boolean = false,
        val missatge: String = "",
        val paisos: List<Country> = listOf(),
    )

}