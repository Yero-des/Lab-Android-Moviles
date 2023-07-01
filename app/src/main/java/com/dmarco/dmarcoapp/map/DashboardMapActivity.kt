package com.dmarco.dmarcoapp.map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dmarco.dmarcoapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class DashboardMapActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_map)

        val locationChanChan = findViewById<ImageView>(R.id.Location)
        val nameChanChan = findViewById<TextView>(R.id.Name_1)
        val locationSipan = findViewById<ImageView>(R.id.Location_2)
        val nameSipan = findViewById<TextView>(R.id.Name_2)
        locationChanChan.setOnClickListener(this)
        nameChanChan.setOnClickListener(this)
        locationSipan.setOnClickListener(this)
        nameSipan.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.Location, R.id.Name_1 -> {
                val intent = Intent(this, MapActivity::class.java)
                intent.putExtra("TITLE", "Chan Chan")
                intent.putExtra("LATITUDE", -8.105530648602553)
                intent.putExtra("LONGITUDE", -79.07461280506742)
                startActivity(intent)
            }
            R.id.Location_2, R.id.Name_2 -> {
                val intent = Intent(this, MapActivity::class.java)
                intent.putExtra("TITLE", "Señor de Sipan")
                intent.putExtra("LATITUDE", -6.704937967478765)
                intent.putExtra("LONGITUDE", -79.89943156835724)
                startActivity(intent)
            }
            else -> return
        }
    }

}