package com.dmarco.dmarcoapp.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dmarco.dmarcoapp.PlatosActivity
import com.dmarco.dmarcoapp.R
import com.dmarco.dmarcoapp.registro.RegisterActivity
import com.dmarco.dmarcoapp.registro.RegisterViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        setup()

    }

    private fun setup() {

        title = "Login"

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val edtCorreo = findViewById<TextInputEditText>(R.id.edtCorreo)
        val edtClave = findViewById<TextInputEditText>(R.id.edtClave)

        val intentRegister = Intent(this, RegisterActivity::class.java)
        val intentPlates = Intent(this, PlatosActivity::class.java)

        btnRegister.setOnClickListener {
            startActivity(intentRegister)
        }

        btnLogin.setOnClickListener {
            val correo = edtCorreo.text.toString()
            val clave = edtClave.text.toString()
            viewModel.login(correo, clave)
        }

        observableViewModel()
    }

    private fun observableViewModel() {
        viewModel.userLoginFirebase.observe(this) { result ->
            val message = when (result) {
                LoginViewModel.LoginResult.SUCCESS -> "Login exitoso"
                LoginViewModel.LoginResult.FAILED-> "El usuario no esta registrado"
                LoginViewModel.LoginResult.EMPTY_FIELDS -> "Por favor, completa todos los campos"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            if (result == LoginViewModel.LoginResult.SUCCESS) {
                startActivity(Intent(this, PlatosActivity::class.java))
                finish()
            }
        }
    }
}