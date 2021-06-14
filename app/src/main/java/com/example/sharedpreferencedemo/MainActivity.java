package com.example.sharedpreferencedemo;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText usernameEdit, passwordEdit;
    Button loginButton;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        usernameEdit = findViewById(R.id.usernameEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        loginButton = findViewById(R.id.loginButton);
        sharedPreferences = getSharedPreferences("MyShared", MODE_PRIVATE);

        getDetails();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                ab.setMessage("Do you want to save the Login Details");

                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        saveDetails();



                    }
                });

                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        saveNoDetails();



                    }
                });


                AlertDialog a = ab.create();
                a.show();

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        getDetails();
    }

    public void saveDetails()
    {
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();


        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("user", username);
        myEdit.putString("pass", password);
        myEdit.apply();


        startActivity(new Intent(MainActivity.this, MainActivity.class));

    }

    public void saveNoDetails()
    {



        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("user", "");
        myEdit.putString("pass", "");
        myEdit.apply();


        startActivity(new Intent(MainActivity.this, MainActivity.class));

    }

    public void getDetails()
    {


        String username = sharedPreferences.getString("user",null);
        String password = sharedPreferences.getString("pass",null);


        usernameEdit.setText(username);
        passwordEdit.setText(password);
    }

}