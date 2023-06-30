package com.dmarco.dmarcoapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dmarco.dmarcoapp.login.LoginActivity


class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar?.hide()

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
        },2000)

    }
}