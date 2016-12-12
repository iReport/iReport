package com.test.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.provider.FirebaseInitProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.Manifest;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.location.Address;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class ReportLittering extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;

    String TAG = "ReportLittering";
    public static int ACCESS_LOCATION_REQUEST_CODE = 323;
    public Location lastLocation;
    public String latitude, longitude;
    private GoogleApiClient googleApiClient;
    TextView lat, longi, street;

    EditText editTextDescription;

    RadioGroup radioGroupSeverityLevel, radioGroupSize;
    RadioButton radioButtonSeverityLevel, radioButtonSize;

    Button buttonSubmit;

    String login_email;
    String fb_email;
    String gmail;

    private DatabaseReference litterdatabase;
    private DatabaseReference reportDatabase;
    private FirebaseAuth firebaseAuth;


//    private DatabaseReference mDatabase;
//    private FirebaseAuth firebaseAuth;

    String latString;
    String longString;
    String DescriptionString;
    String streetAddressString;
    Drawable imageDrawable;
    String sizeString;
    String severityString;

    List<Report> list;

    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_littering);

        Log.i(TAG, "onCreate: ");



        googleApiClientSetUp();
        dispatchTakePictureIntent();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor;
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("lat_string",gmail);
//        editor.commit()



        firebaseAuth=FirebaseAuth.getInstance();
        litterdatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();

        reportDatabase = litterdatabase.child("report");
//        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(sharedPreferences.getString("gmail_address",null)!=null) {
            gmail = sharedPreferences.getString("gmail_address",null);
//            editTextScreenName.setText("");


//            editTextScreenName.setText(gmail);


        } else if(sharedPreferences.getString("fb_address",null)!=null) {
            fb_email = sharedPreferences.getString("fb_address",null);
//            editTextScreenName.setText("");
//
//            editTextScreenName.setText(fb_email);


        } else if(sharedPreferences.getString("login_address",null)!=null) {
            DatabaseReference myRef;
            login_email = sharedPreferences.getString("login_address",null);
//            editTextScreenName.setText("");
//
//            editTextScreenName.setText(login_email);

            // myRef= database.getReference("users").child("email");
            //myRef.setValue(login_email);

        }


      list = new ArrayList<>();
        

        lat = (TextView) findViewById(R.id.lat);
        longi = (TextView) findViewById(R.id.longi);
        street = (TextView) findViewById(R.id.street);
        imageView = (ImageView) findViewById(R.id.imageView2);
        editTextDescription = (EditText) findViewById(R.id.editText);
        buttonSubmit = (Button) findViewById(R.id.button4);



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i(TAG, "onClick: ");

                addListenerOnButton();

//                imageView.getDrawable();

//                Report report = new Report(lat.getText().toString(),longi.getText().toString(),street.getText().toString(),
//                        imageView.getDrawable(),editTextDescription.getText().toString());
//
//                list.add(report);

//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // 'bitmap' is the image returned
//                byte[] b = stream.toByteArray();

//                String b64Image = Base64.encodeToString(b, Base64.DEFAULT);

//                Report report = new Report(lat.getText().toString(),longi.getText().toString(),street.getText().toString(),
//                        imageView.getDrawable(),editTextDescription.getText().toString());

//                list.add(report);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // 'bitmap' is the image returned
                byte[] b = stream.toByteArray();

                String b64Image = Base64.encodeToString(b, Base64.DEFAULT);

//                public void encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                    String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
//                    DatabaseReference ref = FirebaseDatabase.getInstance()
//                            .getReference(Constants.FIREBASE_CHILD_RESTAURANTS)
//                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                            .child(mRestaurant.getPushId())
//                            .child("imageUrl");
//                    ref.setValue(imageEncoded);
//                }


                Log.i(TAG, "onClick: street = "+street.getText().toString());

//                public Report(String longitude, String latitude, String street, String img, String description, String emailId) {


//                    Report report1 = new Report(longi.getText().toString(),lat.getText().toString(),street.getText().toString(),b64Image,editTextDescription.getText().toString(),gmail);
 
                Report report2 = new Report(longi.getText().toString(),lat.getText().toString(),street.getText().toString(),radioButtonSeverityLevel.getText().toString(),b64Image,radioButtonSize.getText().toString(),editTextDescription.getText().toString(),gmail);


//                Report report3 = new Report(longi.getText().toString(),lat.getText().toString(),street.getText().toString(),radioButtonSeverityLevel.getText().toString(),/*b64Image*/imageView.getDrawable(),radioButtonSize.getText().toString(),editTextDescription.getText().toString(),gmail);





//                Report report1 = new Report(lat.getText().toString(),longi.getText().toString(),street.getText().toString(),
//                        b64Image,editTextDescription.getText().toString());



//                litterdatabase.push().setValue(street.getText().toString());
                //litterdatabase.push().setValue(report1);

//                DatabaseReference ref = FirebaseDatabase.getInstance()
//                            .getReference(Constants.FIREBASE_CHILD_RESTAURANTS)
//                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                            .child(mRestaurant.getPushId())
//                            .child("imageUrl");
//                    ref.setValue(imageEncoded);

                reportDatabase.push().setValue(report2);
//                public Report(String longitude, String latitude, String street, String description, Drawable image) {



//                    Report report = new Report(longi.getText().toString(),lat.getText().toString(),
//                        street.getText().toString(),b64Image,editTextDescription.getText().toString());
//
//                list.add(report);





//                public Report(String longitude, String latitude, String street, String currentstatus, String severity, String image, String size, String time, String date, String description, List<User> user, String emailId) {



//                    Report report = new Report(longi.getText().toString(),lat.getText().toString(),
//                        street.getText().toString(),"Not Handled","medium"/*radioButtonSeverityLevel.getText().toString()*/,imageView.getDrawable(),"small"/*radioButtonSize.getText().toString()*/,"time","date",
//                            editTextDescription.getText().toString(),gmail);

//                User person = new User(firstname,lastname,editTextScreenName.getText().toString(),null,address);
//                FirebaseUser user= firebaseAuth.getCurrentUser();
//                mDatabase.child(user.getUid()).setValue(person);

//                mDatabase.child("image").push().setValue(b64Image);


//                Log.i(TAG, "onClick: latitude = "+lat.getText().toString());
//                mDatabase.push().setValue(report1);


//                Log.i(TAG, "onClick: latitude = "+lat.getText().toString());





                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("lat",lat.getText().toString());
                editor.putString("long",longi.getText().toString());
                editor.putString("street",street.getText().toString());
                editor.putString("description",editTextDescription.getText().toString());
                editor.putString("img",imageView.getDrawable().toString());
                editor.commit();

                finish();








            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//            encodeBitmapAndSaveToFirebase(imageBitmap);


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
            //String street = addresses.get(0).getLocality();
            String getLocality = addresses.get(0).getLocality();
            String getPremises = addresses.get(0).getPremises();
            String getSubAdminArea =addresses.get(0).getSubAdminArea();
            String getSubLocality =addresses.get(0).getSubLocality();
            String getSubThoroughfare =addresses.get(0).getSubThoroughfare();
            String getThoroughfare =addresses.get(0).getThoroughfare();

            Log.i(TAG, "saveLocation: address = "+address);
            Log.i(TAG, "saveLocation: city = "+city);
            Log.i(TAG, "saveLocation: state = "+state);
            Log.i(TAG, "saveLocation: street = "+street);
            Log.i(TAG, "saveLocation: postalCode = "+postalCode);
            Log.i(TAG, "saveLocation: knownName = "+knownName);
            Log.i(TAG, "saveLocation: country = "+country);
            Log.i(TAG, "saveLocation: getSubAdminArea = "+getSubAdminArea);
            Log.i(TAG, "saveLocation: getSubLocality = "+getSubLocality);
            Log.i(TAG, "saveLocation: getSubThoroughfare = "+getSubThoroughfare);
            Log.i(TAG, "saveLocation: getThoroughfare = "+getThoroughfare);
            Log.i(TAG, "saveLocation: getLocality = "+getLocality);
            Log.i(TAG, "saveLocation: getPremises = "+getPremises);

            lat.setText(latitude);
            longi.setText(longitude);

            if (getSubThoroughfare == null)
            {
                street.setText(getThoroughfare);
            }
            else
            {
                street.setText(getSubThoroughfare +" "+ getThoroughfare);
            }






            /*streetAddress = (TextView) findViewById(R.id.text_location);
            streetAddress.setText(knownName);*/

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

    public void addListenerOnButton() {

        radioGroupSeverityLevel = (RadioGroup) findViewById(R.id.severityLevel);
        radioGroupSize = (RadioGroup) findViewById(R.id.size);
        int selectedRadioSeverityLevel = radioGroupSeverityLevel.getCheckedRadioButtonId();
        int selectedRadioSize = radioGroupSize.getCheckedRadioButtonId();
        radioButtonSeverityLevel = (RadioButton) findViewById(selectedRadioSeverityLevel);
        radioButtonSize = (RadioButton) findViewById(selectedRadioSize);
        Log.i(TAG, "saveLocation: Severity Level = "+radioButtonSeverityLevel);
        Log.i(TAG, "saveLocation: Size = "+radioButtonSize);

    }
}