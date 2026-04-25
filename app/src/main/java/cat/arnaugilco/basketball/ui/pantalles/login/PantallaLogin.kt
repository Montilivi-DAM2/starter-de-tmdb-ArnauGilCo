package cat.arnaugilco.basketball.ui.pantalles.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cat.arnaugilco.basketball.dades.autenticacio.ControladorDAutenticacio
import cat.arnaugilco.basketball.dades.autenticacio.Resposta
import cat.arnaugilco.basketball.R
import kotlinx.coroutines.launch

@Composable
fun PantallaLogin(
    controladorAutenticacio: ControladorDAutenticacio,
    modifier: Modifier = Modifier,
    navegaAInici: () -> Unit,
    navegaARegistre: () -> Unit
) {

    val ambitCorrutina = rememberCoroutineScope()

    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    var error by remember { mutableStateOf(false) }
    var missatgeError by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "firebase"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Firebase per a Android",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {

                    ambitCorrutina.launch {

                        val resultat = controladorAutenticacio.loginAnonim()

                        when (resultat) {

                            is Resposta.Exit -> {
                                navegaAInici()
                            }

                            is Resposta.Fracas -> {
                                error = true
                                missatgeError = resultat.missatgeError
                            }
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Inici sessió com anònim")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Divider(Modifier.height(2.dp))

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                label = { Text("Correu") },
                value = emailText,
                onValueChange = { emailText = it },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                label = { Text("Password") },
                value = passwordText,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { passwordText = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {

                    ambitCorrutina.launch {

                        val resultat = controladorAutenticacio
                            .loginAmbMailiPassword(emailText, passwordText)

                        when (resultat) {

                            is Resposta.Exit -> {
                                navegaAInici()
                            }

                            is Resposta.Fracas -> {
                                error = true
                                missatgeError = resultat.missatgeError
                            }
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Inici sessió amb email")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Divider(Modifier.height(2.dp))

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "No tens compte? Registra't aquí",
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navegaARegistre()
                }
            )

            if (error) {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = missatgeError,
                    color = Color.Red
                )
            }
        }
    }
}