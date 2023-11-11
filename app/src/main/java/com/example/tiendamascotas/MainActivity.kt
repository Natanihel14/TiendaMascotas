package com.example.tiendamascotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiendamascotas.navigation.MascotaNavigation
import com.example.tiendamascotas.ui.theme.TiendaMascotasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TiendaMascotasTheme {
                // Contenedor principal de la aplicación
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Llama a la función MascotaApp
                    MascotaApp()
                }
            }
        }
    }
}

// Función principal de la aplicación que recibe el controlador de navegación
@Composable
fun MascotaApp() {
    // Superficie que ocupa toda la pantalla con un padding en la parte superior
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 46.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        // Columna que centra su contenido verticalmente y llama a la función de navegación
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Función de navegación
            MascotaNavigation()
        }
    }
}

// Función de saludo que recibe un nombre y un modificador (puede ser utilizado para personalizar la apariencia)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

// Vista previa de la función de saludo
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TiendaMascotasTheme {
        Greeting("Android")
    }
}