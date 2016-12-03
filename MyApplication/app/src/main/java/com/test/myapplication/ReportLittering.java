package com.test.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Geocoder;
import android.location.Location;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import android.Manifest;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.location.Address;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class ReportLittering extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;

    String TAG = "ReportLittering";
    public static int ACCESS_LOCATION_REQUEST_CODE = 323;
    public Location lastLocation;
    public String latitude, longitude;
    private GoogleApiClient googleApiClient;
    TextView streetAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_littering);

        googleApiClientSetUp();
        dispatchTakePictureIntent();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == ACCESS_LOCATION_REQUEST_CODE) {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) {

                saveLocation();

            } else {
                // permission denied !
            }
        }
    }

    private void saveLocation() {

        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},ACCESS_LOCATION_REQUEST_CODE);
            return;

        }

        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if(lastLocation != null) {

            latitude = String.valueOf(lastLocation.getLatitude());
            longitude = String.valueOf(lastLocation.getLongitude());
            Log.i(TAG, "saveLocation: lat = "+latitude);
            Log.i(TAG, "saveLocation: longitude = "+longitude);

            Geocoder geocoder;
            List<Address> addresses = null;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {

                addresses = geocoder.getFromLocation(lastLocation.getLatitude(), lastLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

/*
                addresses = geocoder.getFromLocation(37.3402494, -121.8951709, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
*/


            } catch (IOException e) {
                e.printStackTrace();
            }

            String address = addresses.get(0).getAddressLine(1);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            String street = addresses.get(0).getLocality();
            String abc = addresses.get(0).getLocality();

            Log.i(TAG, "saveLocation: address = "+address);
            Log.i(TAG, "saveLocation: city = "+city);
            Log.i(TAG, "saveLocation: state = "+state);
            Log.i(TAG, "saveLocation: street = "+street);
            Log.i(TAG, "saveLocation: postalCode = "+postalCode);
            Log.i(TAG, "saveLocation: knownName = "+knownName);

            streetAddress = (TextView) findViewById(R.id.text_location);
            streetAddress.setText(knownName);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.location_services_lat),latitude);
            editor.putString(getString(R.string.location_services_long),longitude);
            editor.commit();

        } else {
            Log.i(TAG, "saveLocation: lastlocation is null");
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        saveLocation();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void googleApiClientSetUp(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }
}
