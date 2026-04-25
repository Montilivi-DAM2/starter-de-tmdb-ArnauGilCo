package cat.arnaugilco.basketball.ui.pantalles.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cat.arnaugilco.basketball.R
import cat.arnaugilco.basketball.dades.autenticacio.ControladorDAutenticacio
import cat.arnaugilco.basketball.dades.autenticacio.Resposta
import kotlinx.coroutines.launch

@Composable
fun PantallaRegister(
    controladorAutenticacio: ControladorDAutenticacio,
    modifier: Modifier = Modifier,
    navegaALogin: () -> Unit
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
                contentDescription = "logo"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Crear compte",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                label = { Text("Correu") },
                value = emailText,
                onValueChange = { emailText = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                label = { Text("Password") },
                value = passwordText,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { passwordText = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    ambitCorrutina.launch {

                        val resultat =
                            controladorAutenticacio.registrarAmbMailiPassword(
                                emailText,
                                passwordText
                            )

                        when (resultat) {

                            is Resposta.Exit -> {
                                navegaALogin()
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
                Text("Registrar-se")
            }

            Spacer(modifier = Modifier.height(20.dp))

            ClickableText(
                text = AnnotatedString("Ja tens compte? Inicia sessió"),
                style = TextStyle.Default.copy(
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                navegaALogin()
            }

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