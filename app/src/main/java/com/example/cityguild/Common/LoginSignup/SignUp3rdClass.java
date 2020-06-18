package com.example.cityguild.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityguild.R;


public class SignUp3rdClass extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3rd_class);

        Intent intent = new Intent();
        String getFullName = intent.getStringExtra("fullname");
        String getUsername = intent.getStringExtra("username");
        String getEmail = intent.getStringExtra("email");
        String getPassword = intent.getStringExtra("password");
        String getGender = intent.getStringExtra("gender");
        String date = intent.getStringExtra("date");

    }


    //    login function

    public void callLoginScreenFromSignUp(View view){

        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();

    }

}