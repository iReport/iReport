package com.test.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.NumberFormat;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static android.R.attr.format;
import static android.R.attr.mapViewStyle;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.test.myapplication.R.id.map;

public class MapsActivity extends AppCompatActivity implements GoogleMap.OnInfoWindowClickListener,OnMapReadyCallback{

    SharedPreferences sharedPreferences;
    public String latReport;
    public String longiReport;
    public String streetReport;
    private GoogleMap Mmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        latReport = sharedPreferences.getString("lat",null);
        longiReport = sharedPreferences.getString("long",null);
        streetReport = sharedPreferences.getString("street",null);
    }
    @Override
    public void onMapReady(GoogleMap map) {

        double number = Double.parseDouble(latReport);
        double number2 = Double.parseDouble(longiReport);

        map.addMarker(new MarkerOptions()
                .position(new LatLng(number,number2))
                .title(streetReport));
        Mmap = map;
        map.setOnInfoWindowClickListener(this);

    }

    @Override
    public void onInfoWindowClick(Marker myMarker) {
        Intent intentnew = new Intent(getApplicationContext(),ItemDetailActivity.class);
        startActivity(intentnew);
    }

}