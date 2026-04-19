package cat.arnaugilco.basketball.ui.pantalles

import cat.arnaugilco.basketball.model.players.Player
import cat.arnaugilco.basketball.model.players.response.toPlayer
import cat.arnaugilco.basketball.navegacio.DestPlayers
import androidx.navigation.toRoute
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.arnaugilco.basketball.dades.xarxa.BasketballDBClient
import cat.arnaugilco.basketball.dades.xarxa.BasketballDBHelperImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PantallaJugadorsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private var _estat = MutableStateFlow(JugadorsEstat())
    val estat = _estat.asStateFlow()
    val apiHelper = BasketballDBHelperImpl(BasketballDBClient.servei)

    init {
        val idEquip = savedStateHandle.toRoute<DestPlayers>()
        obtenJugadorsDeEquip(idEquip.teamId)
    }

    fun obtenJugadorsDeEquip(idEquip: Int) {
        viewModelScope.launch {
            apiHelper.getBasketballPlayersFlow(teamId = idEquip)
                .onStart { _estat.update { it.copy(estaCarregant = true) } }
                .flowOn(Dispatchers.IO)
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
                    val jugadors = it.response.map { resp -> resp.toPlayer() }
                    _estat.update {
                        it.copy(
                            estaCarregant = false,
                            jugadors = jugadors,
                            esErroni = false,
                            missatge = ""
                        )
                    }
                }
        }
    }

    data class JugadorsEstat(
        val estaCarregant: Boolean = false,
        val esErroni: Boolean = false,
        val missatge: String = "",
        val jugadors: List<Player> = listOf(),
    )
}