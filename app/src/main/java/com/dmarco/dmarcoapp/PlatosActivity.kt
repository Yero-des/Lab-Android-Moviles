package com.dmarco.dmarcoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmarco.dmarcoapp.map.DashboardMapActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class PlatosActivity : AppCompatActivity() {

    private val listPlatos = listOf(
        Platos("Pizza DMarco", "Pizzas", "S/ 25.00", "La mejor pizza con la combinación perfecta de queso, espinaca y carnes del norte trujillano", R.drawable.imagen1),
        Platos("Duo DMarco", "Bebidas", "S/ 15.00", "La combinación perfecta para compartir entre amigos", R.drawable.imagen2),
        Platos("Pan con Pollo", "Sanguches", "S/ 15.00", "El tradicional pan con pollo trujillano, solo en DMarco", R.drawable.imagen3),
        Platos("Cafe", "Bebidas", "S/ 10.00", "Los mejores granos del norte peruano solo en DMarco", R.drawable.imagen4),
        Platos("Ceviche Tradicional", "Marino", "S/ 25.00", "El infaltable ceviche trujillano", R.drawable.imagen5)

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_platos)

        //BANNER
        MobileAds.initialize(this)
        val adView = findViewById<AdView>(R.id.adView2)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        title = "Platos D'Marco"

        val recyclerPlatos = findViewById<RecyclerView>(R.id.recyclerPlatos)
        recyclerPlatos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PlatosAdapter(listPlatos)
        }

        val btnMap = findViewById<Button>(R.id.btnMap)
        val dashboardMap = Intent(this, DashboardMapActivity::class.java)
        btnMap.setOnClickListener {
            startActivity(dashboardMap)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}