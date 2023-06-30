package com.dmarco.dmarcoapp.login

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmarco.dmarcoapp.PlatosActivity
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel : ViewModel() {
    val userLoginFirebase = MutableLiveData<LoginResult>()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    enum class LoginResult(val errorCode: Int) {
        SUCCESS(0),
        FAILED(1),
        EMPTY_FIELDS(2),
    }

    fun login(email: String, pass: String) {
        if (email.isEmpty() || pass.isEmpty()) {
            userLoginFirebase.value = LoginResult.EMPTY_FIELDS
        } else {
            loginFirebase(email, pass)
        }
    }

    fun loginFirebase(email: String, pass: String) {

        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(LoginActivity()) { task ->
                if (task.isSuccessful) {
                    userLoginFirebase.value = LoginResult.SUCCESS
                } else {
                    userLoginFirebase.value = LoginResult.FAILED
                }
            }
            .addOnFailureListener { exception ->
                Log.e("LoginError", "Error al iniciar sesión: ${exception.message}")
            }
    }


}