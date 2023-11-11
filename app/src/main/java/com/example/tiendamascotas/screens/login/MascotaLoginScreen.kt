package com.example.tiendamascotas.screens.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiendamascotas.navigation.MascotaScreens
import java.sql.RowId

// Composable principal para la pantalla de inicio de sesión de Mascota
@Composable
fun MascotaLoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    // Para controlar si el usuario va a iniciar sesión o crear una cuenta
    // True = Login; False = Create
    val showLoginForm = rememberSaveable{
        mutableStateOf(true)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Renderiza el formulario de inicio de sesión o creación de cuenta según el estado
            if (showLoginForm.value){
                Text(text = "Inicia Sesión")
                UserForm(
                    isCreateAccount = false
                ){
                        email, password ->
                    Log.d("MascotaFeliz", "Logueando con $email y $password")
                    viewModel.signInWithEmailAndPassword(email, password){
                        navController.navigate(MascotaScreens.MascotaHomeScreen.name)
                    }
                }
            }
            else{
                Text(text = "Crear una cuenta")
                UserForm(
                    isCreateAccount = true
                ){
                        email, password ->
                    Log.d("MascotaFeliz", "Creando cuenta con $email y $password")
                    viewModel.createUserWithEmailAndPassword(email, password){
                        navController.navigate(MascotaScreens.MascotaHomeScreen.name)
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            // Enlace para alternar entre inicio de sesión y creación de cuenta
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                val text1 =
                    if (showLoginForm.value) "¿No tienes Cuenta?"
                    else "¿Ya tienes Cuenta?"
                val text2 =
                    if (showLoginForm.value) "Regístrate"
                    else "Inicia Sesión"
                Text(text = text1)
                Text(text = text2,
                    modifier = Modifier
                        .clickable { showLoginForm.value = !showLoginForm.value }
                        .padding(start = 5.dp),
                    color = Color.Blue
                )
            }
        }
    }
}

// Formulario de usuario para iniciar sesión o crear cuenta
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = {email, pwd ->}
) {
    // Estados para el correo electrónico, la contraseña y la visibilidad de la contraseña
    val email = rememberSaveable{
        mutableStateOf("")
    }
    val password = rememberSaveable{
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable{
        mutableStateOf(false)
    }

    // Comprueba si el formulario es válido
    val valido = remember(email.value, password.value){
        email.value.trim().isNotEmpty() &&
                password.value.trim().isNotEmpty()
    }

    // Controlador del teclado
    val keyboardController = LocalSoftwareKeyboardController.current

    // Columna principal del formulario
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        // Campo de entrada para el correo electrónico
        EmailInput(
            emailState = email
        )
        // Campo de entrada para la contraseña
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        // Botón para enviar el formulario
        SubmitButton(
            textId = if (isCreateAccount) "Crear Cuenta" else "Login",
            inputValido = valido
        ){
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

// Botón de envío del formulario
@Composable
fun SubmitButton(
    textId: String,
    inputValido: Boolean,
    onClic: ()->Unit
) {
    Button(onClick = onClic,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValido
    ) {
        Text(text = textId)
    }
}

// Campo de entrada de contraseña con opción de visibilidad
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    // Transformación visual según la visibilidad de la contraseña
    val visualTransformation = if (passwordVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()

    // Campo de entrada de contraseña con icono de visibilidad
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {passwordState.value = it},
        label = { Text(text = labelId)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank()){
                PasswordVisibleIcon(passwordVisible)
            }
            else null
        }
    )
}

// Icono de visibilidad de la contraseña
@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>
) {
    val image =
        if (passwordVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility

    IconButton(onClick = {
        passwordVisible.value = !passwordVisible.value
    }) {
        Icon(
            imageVector=image,
            contentDescription = ""
        )
    }
}

// Campo de entrada de correo electrónico
@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId: String = "Email"
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

// Campo de entrada de texto genérico
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType
) {
    // Campo de entrada de texto
    OutlinedTextField(
        value = valueState.value,
        onValueChange ={valueState.value = it},
        label = { Text(text = labelId)},
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp,)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}
