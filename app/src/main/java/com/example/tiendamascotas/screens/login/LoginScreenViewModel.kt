package com.example.tiendamascotas.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

// ViewModel para la pantalla de inicio de sesión
class LoginScreenViewModel: ViewModel() {

    // Instancia de FirebaseAuth para la autenticación con Firebase
    private val auth: FirebaseAuth = Firebase.auth

    // MutableLiveData para indicar si la aplicación está cargando algo
    private val _loading = MutableLiveData(false)

    // Función para realizar el inicio de sesión con correo electrónico y contraseña
    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("MascotaFeliz", "signInWithEmailAndPassword logueado!!")
                        home()
                    } else {
                        Log.d("MascotaFeliz", "signInWithEmailAndPassword: ${task.result.toString()}")
                    }
                }
        } catch (ex: Exception) {
            Log.d("MascotaFeliz", "signInWithEmailAndPassword: ${ex.message}")
        }
    }

    // Función para crear un nuevo usuario con correo electrónico y contraseña
    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        home()
                    } else {
                        Log.d("MascotaFeliz", " createUserWithEmailAndPassword: ${task.result.toString()}")
                    }
                    _loading.value = false
                }
        }
    }
}