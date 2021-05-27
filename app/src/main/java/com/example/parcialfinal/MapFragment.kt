package com.example.parcialfinal

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener

class MapFragment(val LatLngP: LatLng) : SupportMapFragment(), OnMapReadyCallback, OnSuccessListener<Location>,
    GoogleMap.OnMapLongClickListener {

    private lateinit var map: GoogleMap
    private lateinit var FusionarLocalizacion: FusedLocationProviderClient
    val requestLocationPermissionCode = 10

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FusionarLocalizacion = LocationServices.getFusedLocationProviderClient(requireContext())
        getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap) {
        this.map = p0
        this.map.setOnMapLongClickListener(this)
        FusionarLocalizacion.lastLocation.addOnSuccessListener(this)
        validatePermission()
    }

    override fun onSuccess(p0: Location) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLngP, 15f))
        map.addMarker(MarkerOptions().position(LatLngP).title("Posición Actual"))
    }

    override fun onMapLongClick(p0: LatLng) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLngP, 15f))
        map.addMarker(MarkerOptions().position(LatLngP).title("Destino"))
    }
    private fun validatePermission() {
        if(ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "permiso concedido", Toast.LENGTH_SHORT).show()
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), requestLocationPermissionCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == requestLocationPermissionCode) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "permiso concedido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "El permiso es necesario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}