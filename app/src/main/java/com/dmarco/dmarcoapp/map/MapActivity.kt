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
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var title_site: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        title = "Google Maps"

        latitude = intent.getDoubleExtra("LATITUDE", 0.0)
        longitude = intent.getDoubleExtra("LONGITUDE", 0.0)
        title_site = intent.getStringExtra("TITLE") ?: ""

        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentMap)
                as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(mapGoogle: GoogleMap) {
        map = mapGoogle

        val position = LatLng(latitude,longitude)
        map.addMarker(
            MarkerOptions()
                .position(position)
                .title(title_site)
        )

        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(position, 18f),
            4000,
            null
        )

        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isRotateGesturesEnabled = false
    }

}