package com.example.androidwithjava.google_map;

import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidwithjava.R;
import com.example.androidwithjava.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng latLng = new LatLng(26.2389, 73.0243);
        MarkerOptions markerOptions = new MarkerOptions();

        mMap.addMarker(markerOptions.position(latLng).title("Jodhpur"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f));

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);


        //circle

        CircleOptions circleOptions = new CircleOptions();
        mMap.addCircle(circleOptions
                .center(latLng)
                .radius(200)
                .fillColor(R.color.color_light_blue)
                .strokeColor(R.color.color_dark_blue)
                .strokeColor(R.color.color_stroke)
                .strokeWidth(2f));

        //polygon

        mMap.addPolygon(new PolygonOptions().add(
                                new LatLng(26.2389, 73.0243),
                                new LatLng(26.2390, 73.0244),
                                new LatLng(26.2391, 73.0245),
                                new LatLng(26.2392, 73.0247),
                                new LatLng(26.2393, 73.0248),
                                new LatLng(26.2389, 73.0243)
                        ).fillColor(R.color.color_polygon)
                        .strokeColor(R.color.color_polygon_stroke)

        );

        //groundLay

//        mMap.addGroundOverlay(new GroundOverlayOptions()
//                .position(latLng, 1000f, 1000f)
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.ic_contact_profile))
//                .clickable(true));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                mMap.addMarker(markerOptions
                        .position(latLng)
                        .title(latLng.toString())
                );

                Geocoder geocoder = new Geocoder(MapsActivity.this);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    // Android 13 (API 33) and above - Asynchronous Listener
                    geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1, new Geocoder.GeocodeListener() {
                        @Override
                        public void onGeocode(@NonNull List<Address> addresses) {
                            if (!addresses.isEmpty()) {
                                Address address = addresses.get(0);
                                // Process address details here on background thread callback
                                Log.d("MapsActivity", "Address: " + address.getAddressLine(0));
                            }
                        }

                        @Override
                        public void onError(String errorMessage) {
                            Log.e("MapsActivity", "Geocoding error: " + errorMessage);
                        }
                    });
                } else {
                    // Below Android 13 - Legacy Synchronous Call
                    try {
                        ArrayList<Address> addresses = (ArrayList<Address>) geocoder.getFromLocation(26.2389, 73.0243, 1);
                        if (addresses != null && !addresses.isEmpty()) {
                            Address address = addresses.get(0);
                            Log.d("MapsActivity", "Address: " + address.getAddressLine(0));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        binding.radioGroup.setOnCheckedChangeListener((group, itemId) -> {
            if (itemId == R.id.radio_normal) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            } else if (itemId == R.id.radio_satellite) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            } else if (itemId == R.id.radio_hybrid) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            } else if (itemId == R.id.radio_terrain) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

    }
}