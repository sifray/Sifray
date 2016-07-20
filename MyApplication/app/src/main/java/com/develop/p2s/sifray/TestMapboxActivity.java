package com.develop.p2s.sifray;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
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
    MarkerOptions mark [] = new MarkerOptions[30];
    String dataMark[][] = new String[30][4];
    // nama, latitude, longitude
    //kumpulan data

    //end

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
    int x;
    Firebase myFirebaseRef;
    String ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mapbox);

        //dataMark
        x = 0;
        //data0
        dataMark[x][0] = "Pendulum 360";
        dataMark[x][1] = "7.882657";
        dataMark[x][2] = "112.525811";
        dataMark[x][3] = "DESKRIPSI Pendulum 360";

        //firebase
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://sifray-dba59.firebaseio.com/");
        ref = "data"+x;
        onPause();
        ambilData();
        onResume();
        testButton = (Button) findViewById(R.id.btn);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Masook", dataMark[0][0]);

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
                mark[0] = new MarkerOptions()
                        .position(new LatLng(-(Double.parseDouble(dataMark[0][1])), Double.parseDouble(dataMark[0][2]))).title(dataMark[0][0])
                        .snippet(dataMark[0][3]);
                mapboxMap.addMarker(mark[0]);
                mulai(mapboxMap);
            }

        });



    }

    // Add the mapView lifecycle to the activity's lifecycle methods
    void ambilData(){
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                dataMark[0][0] = snapshot.getValue() + "";
                Log.d("Masook", dataMark[0][0]);

            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
    }
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
