package com.example.tiendamascotas.screens.home

import android.app.ListActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiendamascotas.List3Activity
import com.example.tiendamascotas.R
import com.example.tiendamascotas.SearchActivity
import com.example.tiendamascotas.ui.theme.TiendaMascotasTheme

// Pantalla principal de la aplicación que muestra opciones de navegación
@Composable
fun Elementos() {
    val mContext = LocalContext.current

    // Columna principal que organiza los elementos en posición vertical
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título de la tienda
        Text(
            "Tienda de Mascotas",
            color = Color.Blue,
            style = MaterialTheme.typography.displaySmall
        )

        // Subtítulo de la tienda
        Text(
            "Ptae",
            color = Color.Blue,
            fontSize = 28.sp
        )

        // Mensaje de bienvenida
        Text(text = "Bienvenido")

        // Espaciador horizontal
        Spacer(modifier = Modifier.width(10.dp))

        // Caja que contiene una imagen de una mascota
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.secondary)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fotohome),
                contentDescription = "Mascota"
            )
            Spacer(modifier = Modifier.width(10.dp))
        }

        // Botones de navegación
        OutlinedButton(
            onClick = { mContext.startActivity(Intent(mContext, com.example.tiendamascotas.ListActivity::class.java)) }
        ) {
            Text(
                text = "Catálogo de Mascotas",
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        OutlinedButton(
            onClick = { mContext.startActivity(Intent(mContext, com.example.tiendamascotas.List2Activity::class.java)) }
        ) {
            Text(
                text = "Catálogo de Alimentos",
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        OutlinedButton(
            onClick = { mContext.startActivity(Intent(mContext, List3Activity::class.java)) }
        ) {
            Text(
                text = "Catálogo de Accesorios",
                color = Color.Black
            )
        }
        OutlinedButton(
            onClick = { mContext.startActivity(Intent(mContext, SearchActivity::class.java)) }
        ) {
            Text(
                text = "Inventario",
                color = Color.Black
            )
        }
    }
}

// Función principal que representa la pantalla de inicio de la aplicación
@Composable
fun Home(navController: NavController) {
    TiendaMascotasTheme {
        Elementos()
    }
}