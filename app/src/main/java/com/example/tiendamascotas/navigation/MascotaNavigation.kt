package com.example.tiendamascotas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiendamascotas.screens.MascotaSplashScreen
import com.example.tiendamascotas.screens.home.Home
import com.example.tiendamascotas.screens.login.MascotaLoginScreen

/*Función para controlar el controller*/
@Composable
fun MascotaNavigation(){
    val navController = rememberNavController()
    /*componente Nav Host que contiene el parámetro startDestination*/
    NavHost(
        navController = navController,
        startDestination = MascotaScreens.SplashScreen.name
    ){
        /* Tres rutas de navegación*/
        composable(MascotaScreens.SplashScreen.name){
            MascotaSplashScreen(navController = navController)
        }
        composable(MascotaScreens.LoginScreen.name){
            MascotaLoginScreen(navController = navController)
        }
        composable(MascotaScreens.MascotaHomeScreen.name){
            Home(navController = navController)
        }
    }
}