package com.test.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Asmita Deshpande on 12/5/16.
 */



public class Resident_features_settings extends AppCompatActivity {

    private static final String TAG = "Resident_features";


    CheckBox email_confirmation;
    CheckBox email_notification;
    CheckBox report_anonymously;

    EditText editTextScreenName;

    String login_email;
    String fb_email;
    String gmail;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_settings);


        Log.i(TAG, "onCreate: ");
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email_confirmation = (CheckBox)findViewById(R.id.checkBox);
        email_notification = (CheckBox)findViewById(R.id.checkBox1);
        report_anonymously = (CheckBox)findViewById(R.id.checkBox2);

        editTextScreenName = (EditText) findViewById(R.id.editText2);


        boolean email_confirmationChecked = email_confirmation.isChecked();
        boolean email_notificationChecked = email_notification.isChecked();
        boolean report_anonymouslyChecked = report_anonymously.isChecked();

        Intent intent = getIntent();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if(sharedPreferences.getString("gmail_address",null)!=null) {
            gmail = sharedPreferences.getString("gmail_address",null);
            editTextScreenName.setText("");


            editTextScreenName.setText(gmail);


        } else if(sharedPreferences.getString("fb_address",null)!=null) {
            fb_email = sharedPreferences.getString("fb_address",null);
            editTextScreenName.setText("");

            editTextScreenName.setText(fb_email);


        } else if(sharedPreferences.getString("login_address",null)!=null) {
            login_email = sharedPreferences.getString("login_address",null);
            editTextScreenName.setText("");

            editTextScreenName.setText(login_email);

        }








    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox2:
                if (checked)
                {
                    email_confirmation.setChecked(false);
                    email_notification.setChecked(false);

                }
                else
                {
                    email_confirmation.setChecked(true);
                    email_notification.setChecked(true);
                }
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
