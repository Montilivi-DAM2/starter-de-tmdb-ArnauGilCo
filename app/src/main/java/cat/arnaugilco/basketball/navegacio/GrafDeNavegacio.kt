package cat.arnaugilco.basketball.navegacio

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.arnaugilco.basketball.ui.pantalles.PantallaEquips
import cat.arnaugilco.basketball.ui.pantalles.PantallaJugadors
import cat.arnaugilco.basketball.ui.pantalles.PantallaLligues
import cat.arnaugilco.basketball.ui.pantalles.PantallaSeleccionDePais
import cat.arnaugilco.basketball.ui.pantalles.general.PantallaInstruccions
import cat.arnaugilco.basketball.ui.pantalles.general.PantallaPortada
import cat.montilivi.tmdb.ui.pantalles.PantallaPreferencies
import cat.montilivi.tmdb.ui.pantalles.PantallaQuantA

@Composable
fun GrafDeNavegacio(controladorDeNavegacio: NavHostController = rememberNavController(), modifier: Modifier = Modifier)
{
    NavHost(navController = controladorDeNavegacio, startDestination = DestLandingPage, modifier = modifier)
    {
        composable<DestLandingPage>
        {
            PantallaPortada()
        }
        composable<DestCountrySelection> {
            PantallaSeleccionDePais(
                onClick = { countryCode ->
                    controladorDeNavegacio.navigate(
                        DestLeagues(countryCode)
                    )
                }
            )
        }
        composable<DestLeagues>
        {backStackEntry ->
            val parametre = backStackEntry.toRoute<DestLeagues>()
            PantallaLligues(
                onClick = { leagueId ->
                    controladorDeNavegacio.navigate(
                        DestTeams(leagueId)
                    )
                }
            )
        }
        composable<DestTeams>
        {backStackEntry ->
            val parametre = backStackEntry.toRoute<DestTeams>()
            PantallaEquips(
                onClick = { teamId ->
                    controladorDeNavegacio.navigate(
                        DestPlayers(teamId)
                    )
                }
            )
        }
        composable<DestPlayers>
        {backStackEntry ->
            val parametre = backStackEntry.toRoute<DestPlayers>()
            PantallaJugadors()
        }
        composable<DestPreferences>
        {
            PantallaPreferencies()
        }
        composable<DestSteps>
        {
            PantallaInstruccions()
        }
        composable<DestAbout>
        {
            PantallaQuantA()
        }
    }
}