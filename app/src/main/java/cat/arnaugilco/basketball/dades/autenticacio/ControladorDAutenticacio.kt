package cat.arnaugilco.basketball.dades.autenticacio

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class ControladorDAutenticacio(private val context: Context) {
    private val tag = "CONTROLADOR_D_AUTENTICACIO"
    private val ID_CLIENT = ""

    private val autentificacio: FirebaseAuth by lazy {
        Firebase.auth
    }

    private val controladorDAutenticacio = CredentialManager.create(context)

    fun obtenirUsuariActual(): FirebaseUser? {
        return autentificacio.currentUser
    }

    suspend fun tancaSessio() {
        try {
            controladorDAutenticacio.clearCredentialState(
                ClearCredentialStateRequest()
            )
        } catch (e: Exception) {
        }

        autentificacio.signOut()
    }

    fun hiHaUsuariIniciat() = obtenirUsuariActual() != null

    suspend fun loginAmbMailiPassword(correu: String, motDePas: String): Resposta<FirebaseUser?> {
        try {
            val resultat = autentificacio.signInWithEmailAndPassword(correu, motDePas).await()
            val usuari = resultat.user
            if (usuari == null) throw Exception("Error inici de sessió")
            return Resposta.Exit(usuari)
        } catch (e: Exception) {
            return Resposta.Fracas("Error inici de sessió: ${e.message}")
        }
    }

    suspend fun loginAnonim(): Resposta<FirebaseUser?> {
        try {
            val resultat = autentificacio.signInAnonymously().await()
            val usuari = resultat.user
            if (usuari == null) throw Exception("Error inici de sessió")
            return Resposta.Exit(usuari)
        } catch (e: Exception) {
            return Resposta.Fracas("Error inici de sessió: ${e.message}")
        }
    }

    suspend fun registrarAmbMailiPassword(correu: String, motDePas: String): Resposta<FirebaseUser?> {
        try {
            val resultat = autentificacio.createUserWithEmailAndPassword(correu, motDePas).await()
            val usuari = resultat.user
            if (usuari == null) throw Exception("Error registre")
            return Resposta.Exit(usuari)
        } catch (e: Exception) {
            return Resposta.Fracas("Error registre: ${e.message}")
        }
    }
}