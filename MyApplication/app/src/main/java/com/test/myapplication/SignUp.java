package com.test.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        Intent intent = getIntent();
        final TextView tv = (TextView) findViewById(R.id.link_login);

        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }
}
