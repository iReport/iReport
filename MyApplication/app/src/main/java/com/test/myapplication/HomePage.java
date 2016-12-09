package com.test.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Asmita Deshpande on 12/5/16.
 */

public class HomePage extends AppCompatActivity {

    private static final String TAG = "HomePage";


    String login_email;
    String fb_email;
    String gmail;

    Button buttonReport;
    Button buttonMyReports;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_features);
        //Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar2);
        //setSupportActionBar(myToolbar);



        Intent intent = getIntent();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        gmail = sharedPreferences.getString("gmail_address",null);
//        fb_email = sharedPreferences.getString("fb_address",null);
//        login_email = sharedPreferences.getString("login_address",null);


        if(sharedPreferences.getString("gmail_address",null)!=null) {
            gmail = sharedPreferences.getString("gmail_address",null);
//            editTextScreenName.setText(gmail);


        } else if(sharedPreferences.getString("fb_address",null)!=null) {
            fb_email = sharedPreferences.getString("fb_address",null);
//            editTextScreenName.setText(fb_email);


        } else if(sharedPreferences.getString("login_address",null)!=null) {
            login_email = sharedPreferences.getString("login_address",null);
//            editTextScreenName.setText(login_email);

        }



        Log.i(TAG, "onCreate: latitude = "+sharedPreferences.getString("lat",null));



        Log.i(TAG, "onCreate: ");

        initializeViews();

        buttonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,ReportLittering.class);
                startActivity(intent);
            }
        });

        buttonMyReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,ItemListActivity.class);
                startActivity(intent);
            }
        });
        Log.i(TAG, "onCreate: HOMEPAGE");




//        lat = sharedPreferences.getString(getString(R.string.location_services_lat),null);
//        longi = sharedPreferences.getString(getString(R.string.location_services_long),null);



//        if(intent.getStringExtra("login_gmail")!= null) {
//            login_email = intent.getStringExtra("login_gmail");
//        }



//        Bundle bundle = getIntent().getExtras();
//
//        if(bundle != null) {
//
//            Log.i(TAG, "onCreate: bundle = " + bundle);
//
//            login_email = bundle.getString("login_gmail");
//
//            Log.i(TAG, "onCreate: email == " + login_email);
//        }


        
//        if(intent.getStringExtra("login")!=null) {
//            login_email = intent.getStringExtra("login");
//
//            Log.i(TAG, "onCreate: ~~~~~~~~~~~~~~~~~~~~~");
//
//            Log.i(TAG, "onCreate: email = "+login_email);
//
//        }








//        if(intent!=null) {
//
//            Log.i(TAG, "onCreate: ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//            if(!(intent.getStringExtra("login").equals(""))) {
//                login_email = intent.getStringExtra("login");
//                Log.i(TAG, "onCreate: login");
//                Log.i(TAG, "onCreate: "+login_email);
//
//
//            } else if (!(intent.getStringExtra("login_fb").equals(""))) {
//                fb_email = intent.getStringExtra("login_fb");
//
//                Log.i(TAG, "onCreate: fb");
//
//
//            }  else if (!(intent.getStringExtra("login_gmail").equals(""))) {
//                gmail = intent.getStringExtra("login_gmail");
//
//                Log.i(TAG, "onCreate: gmail");
//
//                Log.i(TAG, "onCreate: "+gmail);
//
//            }
//        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {
//            Toast.makeText(this, "User profile clicked: "+login_email, Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(),Resident_features_settings.class);
            startActivity(i);

            return true;
        }

        if (id == R.id.logout) {

            FirebaseAuth.getInstance().signOut();


            finish();
        }
        if (id == R.id.maps) {

            Intent i = new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeViews() {
        buttonMyReports = (Button) findViewById(R.id.button_myreports);
        buttonReport = (Button) findViewById(R.id.button_report);

    }
}
