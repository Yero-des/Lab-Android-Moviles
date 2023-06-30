package com.dmarco.dmarcoapp.registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dmarco.dmarcoapp.R
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        //Setup
        setup()
    }

    private fun setup() {
        title = "Autenticacion"
        val edtNombre = findViewById<TextInputEditText>(R.id.edtNombre)
        val edtApellido = findViewById<TextInputEditText>(R.id.edtApellido)
        val edtCorreo = findViewById<TextInputEditText>(R.id.edtCorreo)
        val edtClave = findViewById<TextInputEditText>(R.id.edtClave)
        val edtClaveRepetir = findViewById<TextInputEditText>(R.id.edtClaveRepetir)

        val btnRegister = findViewById<Button>(R.id.btnRegistrar)

        btnRegister.setOnClickListener {
            val nombre = edtNombre.text.toString()
            val apellidos = edtApellido.text.toString()
            val correo = edtCorreo.text.toString()
            val clave = edtClave.text.toString()
            val repClave = edtClaveRepetir.text.toString()
            viewModel.register(nombre, apellidos, correo, clave, repClave)
        }
        observableViewModel()
    }

    private fun observableViewModel() {
        viewModel.userRegisterFirebase.observe(this) { result ->
            val message = when (result) {
                RegisterViewModel.RegistrationResult.SUCCESS -> "Registro correcto"
                RegisterViewModel.RegistrationResult.ERROR -> "Error de registro"
                RegisterViewModel.RegistrationResult.ERRORDB -> "Error de registro en base de datos"
                RegisterViewModel.RegistrationResult.ERRORNULL -> "Error de datos usuario nulo"
                RegisterViewModel.RegistrationResult.PASSWORD_MISMATCH -> "Las contraseñas no coinciden"
                RegisterViewModel.RegistrationResult.EMPTY_FIELDS -> "Por favor, completa todos los campos"
                RegisterViewModel.RegistrationResult.INVALID_PASSWORD_LENGTH -> "La contraseña debe tener 6 digitos minimo"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            if (result == RegisterViewModel.RegistrationResult.SUCCESS) {
                finish()
            }
        }
    }
}