package com.example.tiendamascotas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tiendamascotas.ui.theme.TiendaMascotasTheme
import android.content.Intent
import android.graphics.Paint.Style
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiendamascotas.navigation.MascotaScreens
import com.example.tiendamascotas.screens.home.Elementos
import com.example.tiendamascotas.ui.theme.TiendaMascotasTheme
import com.google.api.Distribution.BucketOptions.Linear

class List2Activity : ComponentActivity() {
    // Lista de artículos
    val listado : List<Articulo> = listOf(
        Articulo(R.drawable.cachorro, "Cachorro", "Precio: Q10"),
        Articulo(R.drawable.adulto, "Adulto", "Precio: Q20"),
        Articulo(R.drawable.tortolaa, "Tórtolas", "Precio: Q50"),
        Articulo(R.drawable.gallinaa, "Gallinas", "Precio: Q125"),
        Articulo(R.drawable.pezz, "Peces", "Precio: Q500")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TiendaMascotasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Pantalla2(listado)
                }
            }
        }
    }
}

@Composable
fun Pantalla2(listado: List<Articulo>) {
    // LazyColumn para mostrar la lista de artículos
    LazyColumn(
        contentPadding = PaddingValues(all = 28.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(listado) { item ->
            // Item de la lista
            ListItemRow2(item)
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun ListItemRow2(item: Articulo) {
    // Contexto actual
    val mContext = LocalContext.current
    // Estado para manejar la información expandida de cada artículo
    var masInformacion = remember { mutableStateOf(false) }
    // Estado para mostrar la descripción (agregado)
    var mostrarDescripcion = remember { mutableStateOf(false) }

    // Contenedor principal (Box)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        // Fila con información del artículo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Imagen del artículo
            Image(
                painter = painterResource(id = item.image),
                contentDescription = item.title,
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = tween(70, 0, LinearEasing)
                    )
                    .size(115.dp)
            )
            // Columna con detalles del artículo
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
            ) {
                // Nombre del artículo
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Detalles del artículo (se muestra si masInformacion es true)
                if (masInformacion.value)
                    Text(
                        text = item.details,
                        style = MaterialTheme.typography.bodyLarge
                    )
            }

            // Columna con botones (Ver precio y Comprar)
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                // Botón "Ver precio"
                OutlinedButton(
                    onClick = {
                        masInformacion.value = !masInformacion.value
                    }
                ) {
                    Text(if (masInformacion.value) "Ocultar" else "Ver precio", color = Color.Black)
                }

                // Nuevo botón "Comprar" con acciones personalizadas
                OutlinedButton(
                    onClick = {
                        // Acciones para el botón "Comprar"
                        // Puedes personalizar la lógica para cada artículo aquí
                        when (item.title) {
                            "Cachorro" -> {
                                Toast.makeText(
                                    mContext,
                                    "Añadido Cachorro",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            "Adulto" -> {
                                Toast.makeText(
                                    mContext,
                                    "Añadido Adulto",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            "Tórtolas" -> {
                                Toast.makeText(
                                    mContext,
                                    "Añadido Tórtolas",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            "Gallinas" -> {
                                Toast.makeText(
                                    mContext,
                                    "Añadido Gallinas",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            "Peces" -> {
                                Toast.makeText(
                                    mContext,
                                    "Añadido Peces",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Toast.makeText(
                                    mContext,
                                    "Comprar ${item.title}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                ) {
                    Text("Comprar", color = Color.Black)
                }
            }
        }

        // Mostrar descripción (agregado)
        if (mostrarDescripcion.value) {
            Box(
                modifier = Modifier
                    .background(color = Color.Gray)
                    .padding(8.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun prueba2() {
    // Función de prueba
}

@Preview(showBackground = true)
@Composable
fun PantallaPreview2() {
    // Vista previa de la pantalla con artículos de ejemplo
    val listado : List<Articulo> = listOf(
        Articulo(R.drawable.cachorro, "Cachorro", "Precio: Q10"),
        Articulo(R.drawable.adulto, "Adulto", "Precio: Q20"),
        Articulo(R.drawable.tortolaa, "Tórtolas", "Precio: Q50"),
        Articulo(R.drawable.gallinaa, "Gallinas", "Precio: Q60"),
        Articulo(R.drawable.pezz, "Peces", "Precio: Q70")
    )
    TiendaMascotasTheme {
        Pantalla2(listado)
    }
}