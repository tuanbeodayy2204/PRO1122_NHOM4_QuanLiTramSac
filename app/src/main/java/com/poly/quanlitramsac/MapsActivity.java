package com.poly.quanlitramsac;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Khởi tạo Google Maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Lấy dữ liệu từ Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            double latitude = extras.getDouble("latitude");
            double longitude = extras.getDouble("longitude");
            String address = extras.getString("address");

            // Thêm marker và di chuyển camera
            LatLng location = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(location).title(address));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        }
    }
}
