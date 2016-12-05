package com.test.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUp";


    TextView textViewLoginLink;
    Button buttonSignup;
    EditText editTextName;
    EditText editTextEmail;
    EditText editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        Intent intent = getIntent();

        initializeViews();

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String name = editTextName.getText().toString();
                String password = editTextPassword.getText().toString();

                if(email.equals("")) {
                    editTextEmail.setError("This field is required!");
                    final AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                    builder.setTitle("Alert!")
                            .setMessage("Please enter a valid Email address")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    builder.create().dismiss();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else if(password.equals("")) {
                    editTextPassword.setError("This field is required!");
                    final AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                    builder.setTitle("Alert!")
                            .setMessage("Please enter a Password")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    builder.create().dismiss();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {

                    Log.i(TAG, "onClick: signup email = "+email);
                    Log.i(TAG, "onClick: signup password = "+password);
                    Log.i(TAG, "onClick: signup name = "+name);
                }
            }
        });


        textViewLoginLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });




    }

    private void initializeViews() {
        textViewLoginLink = (TextView) findViewById(R.id.link_login);
        buttonSignup = (Button) findViewById(R.id.btn_signup);
        editTextName = (EditText) findViewById(R.id.input_name_signup);
        editTextEmail = (EditText) findViewById(R.id.input_email_signup);
        editTextPassword = (EditText) findViewById(R.id.input_password_signup);

    }
}
