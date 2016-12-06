package com.test.myapplication;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Asmita Deshpande on 12/5/16.
 */



public class Resident_features_settings extends AppCompatActivity {
    CheckBox email_confirmation;
    CheckBox email_notification;
    CheckBox report_anonymously;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email_confirmation = (CheckBox)findViewById(R.id.checkBox);
        email_notification = (CheckBox)findViewById(R.id.checkBox1);
        report_anonymously = (CheckBox)findViewById(R.id.checkBox2);
        boolean email_confirmationChecked = email_confirmation.isChecked();
        boolean email_notificationChecked = email_notification.isChecked();
        boolean report_anonymouslyChecked = report_anonymously.isChecked();

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
