package com.develop.p2s.sifray;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapzen.android.lost.api.LocationServices;

public class TestMapboxActivity extends AppCompatActivity {

    private MapView mapView;
    private Button testButton;
    FloatingActionButton floatingActionButton;
    LocationServices locationServices;
    MarkerOptions mark1;
    MapboxMap mapboxMap;

    protected void mulai(MapboxMap map) {
        //iseng
        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(-7.884515, 112.524654)) // Sets the new camera position
                .zoom(16.6) // Sets the zoom
                .bearing(359) // Rotate the camera
                .tilt(15) // Set the camera tilt
                .build(); // Creates a CameraPosition from the builder
        //mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position),7000);
        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 7000);
        //batas iseng
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mapbox);



        testButton = (Button) findViewById(R.id.btn);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Create a mapView
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(final MapboxMap mapboxMap) {
                mapboxMap.setStyleUrl("mapbox://styles/mapbox/streets-v9");
                // Customize map with markers, polylines, etc.
                mark1 = new MarkerOptions()
                        .position(new LatLng(-7.884501, 112.524800)).title("TEST JATIM PARK")
                        .snippet("o aza ya kan~");
                mapboxMap.addMarker(mark1);
                mulai(mapboxMap);

            }

        });



    }

    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
