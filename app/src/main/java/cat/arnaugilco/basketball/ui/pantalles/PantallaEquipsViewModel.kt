package cat.arnaugilco.basketball.ui.pantalles

import cat.arnaugilco.basketball.model.teams.Team
import cat.arnaugilco.basketball.model.teams.response.toTeam
import cat.arnaugilco.basketball.navegacio.DestTeams
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

class PantallaEquipsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private var _estat = MutableStateFlow(EquipsEstat())
    val estat = _estat.asStateFlow()
    val apiHelper = BasketballDBHelperImpl(BasketballDBClient.servei)

    init {
        val idLeague = savedStateHandle.toRoute<DestTeams>()
        obtenEquipsDeLliga(idLeague.leagueId)
    }

    fun obtenEquipsDeLliga(idLeague: Int) {
        viewModelScope.launch {
            apiHelper.getBasketballTeamsFlow(leagueId = idLeague)
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
                    val equips = it.response.map { resp -> resp.toTeam() }
                    _estat.update {
                        it.copy(
                            estaCarregant = false,
                            equips = equips,
                            esErroni = false,
                            missatge = ""
                        )
                    }
                }
        }
    }

    data class EquipsEstat(
        val estaCarregant: Boolean = false,
        val esErroni: Boolean = false,
        val missatge: String = "",
        val equips: List<Team> = listOf(),
    )
}