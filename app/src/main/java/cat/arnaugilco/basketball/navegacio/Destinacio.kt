package cat.arnaugilco.basketball.navegacio

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.outlined.Adb
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.SportsBasketball
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
object DestLandingPage

@Serializable
object DestPreferences

@Serializable
object DestSteps

@Serializable
object DestAbout

@Serializable
object DestCountrySelection

@Serializable
data class DestLeagues(val countryId: String)

@Serializable
data class DestTeams(val leagueId: Int)

@Serializable
data class DestPlayers(val teamId: Int)

data class EtiquetaDelDrawer<T:Any>(val ruta:T, val iconaNoSeleccionada:ImageVector, val iconaSeleccionada:ImageVector, val titol:String, val teBadge: Boolean =false, val badge: ImageVector = Icons.Filled.Badge, val tintBadge: Color =  Color.Red)

val etiquetesDelDrawer = listOf(
    EtiquetaDelDrawer(DestLandingPage, iconaNoSeleccionada = Icons.Outlined.Home, iconaSeleccionada = Icons.Filled.Home, titol = "Portada"),
    EtiquetaDelDrawer(DestCountrySelection, iconaNoSeleccionada = Icons.Outlined.SportsBasketball, iconaSeleccionada = Icons.Filled.SportsBasketball, titol = "Bàsquet"),
    EtiquetaDelDrawer(DestPreferences, iconaNoSeleccionada = Icons.Outlined.Build, iconaSeleccionada = Icons.Filled.Build, titol = "Preferències"),
    EtiquetaDelDrawer(DestSteps, iconaNoSeleccionada = Icons.Outlined.Info, iconaSeleccionada = Icons.Filled.Info, titol = "Instruccions"),
    EtiquetaDelDrawer(DestAbout, iconaNoSeleccionada = Icons.Outlined.Adb, iconaSeleccionada = Icons.Filled.Adb, titol = "Quant a..."),
)