package com.example.tiendamascotas.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiendamascotas.navigation.MascotaScreens
import kotlinx.coroutines.delay

// Pantalla de presentación con animación antes de navegar a la pantalla de inicio de sesión
@Composable
fun MascotaSplashScreen(navController: NavController){
    /* Hará una animación y luego abrira otra pantalla  */
    val scale = remember{
        androidx.compose.animation.core.Animatable(0f)
    }

// Efecto lanzado para la animación y la navegación
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(durationMillis = 800,
            easing = {
                OvershootInterpolator(8f)
                    .getInterpolation(it)
            }
                ),


        )
        delay(3500L)
        // Navegación hacia la pantalla de inicio de sesión
        navController.navigate(MascotaScreens.LoginScreen.name)
    }
    // Color primario del tema
    val color = MaterialTheme.colorScheme.primary
    // Superficie circular con nombre de la aplicación y animación de escala aplicada
    Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)

        .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = 2.dp, color = color )

    ) {
        // Columna que organiza el contenido en posición vertical
        Column(modifier = Modifier
            .padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Texto del nombre de la aplicación con estilo y color
            Text("Ptae",
            style = MaterialTheme.typography.headlineMedium,
                color = color.copy(alpha = 0.5f)
            )
            // Espaciador vertical
            Spacer(modifier = Modifier.height(12.dp))
            // Texto del eslogan con estilo y color
            Text("Lo que tu mascota necesita",
                style = MaterialTheme.typography.headlineSmall,
                color = color.copy(alpha = 0.5f)
            )
        }
    }
}