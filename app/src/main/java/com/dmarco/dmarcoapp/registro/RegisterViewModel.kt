package com.dmarco.dmarcoapp.registro

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel : ViewModel() {

    val userRegisterFirebase = MutableLiveData<RegistrationResult>()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    enum class RegistrationResult(val errorCode: Int) {
        SUCCESS(0),
        ERROR(1),
        PASSWORD_MISMATCH(2),
        EMPTY_FIELDS(3),
        ERRORDB(4),
        ERRORNULL(5),
        INVALID_PASSWORD_LENGTH(6)
    }

    fun register(nombre: String, apellidos: String, correo: String, clave: String, repClave: String) {
        // Verificar que las contraseñas coincidan
        if (nombre.isNotEmpty() && apellidos.isNotEmpty() && correo.isNotEmpty() && clave.isNotEmpty()) {
            if (clave.length >= 6) {
                if (clave == repClave) {
                    registerFirebase(nombre, apellidos, correo, clave)
                } else {
                    userRegisterFirebase.value = RegistrationResult.PASSWORD_MISMATCH
                }
            } else {
                userRegisterFirebase.value = RegistrationResult.INVALID_PASSWORD_LENGTH
            }
        } else {
            userRegisterFirebase.value = RegistrationResult.EMPTY_FIELDS
        }

    }

    private fun registerFirebase(nombre: String, apellidos: String, email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = task.result?.user?.uid

                    if (userId != null) {
                        registrarFirestore(userId, nombre, apellidos, email)
                    } else {
                        userRegisterFirebase.value = RegistrationResult.ERRORNULL
                    }

                } else {
                    val exception = task.exception
                    Log.v("ERROR", exception.toString())
                    userRegisterFirebase.value = RegistrationResult.ERROR
                }
            } .addOnFailureListener { exception ->
                Log.e("CreateError", "Error al iniciar sesión: ${exception.message}")
            }
    }

    private fun registrarFirestore(uid: String, nombre: String, apellidos: String, correo: String) {
        val usuarioFirebase = UsuarioFirebase(nombre, apellidos, correo)
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("Usuario").document(uid).set(usuarioFirebase)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userRegisterFirebase.value = RegistrationResult.SUCCESS
                } else {
                    userRegisterFirebase.value = RegistrationResult.ERRORDB
                }
            }
            .addOnFailureListener { exception ->
                Log.e("LoginError", "Error al iniciar sesión: ${exception.message}")
            }
    }


}




