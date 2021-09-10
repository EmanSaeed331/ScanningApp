package com.example.lenovo.scannapp.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.scannapp.R;
import com.example.lenovo.scannapp.navActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button StartButton = (Button)findViewById(R.id.startbtn);

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(SplashActivity.this);
                View view1 = getLayoutInflater().inflate(R.layout.logindialog , null);
                EditText userName = (EditText)view1.findViewById(R.id.userNameET);
                EditText Password = (EditText)view1.findViewById(R.id.PasswordET);
                Button Loginbtn = (Button)view1.findViewById(R.id.LoginBtn);


                mbuilder.setView(view1);
                AlertDialog dialog = mbuilder.create();
                dialog.show();

                Loginbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(SplashActivity.this , navActivity.class);
                        startActivity(intent);


                    }
                });


            }


        });


    }
}
